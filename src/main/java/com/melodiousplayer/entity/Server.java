package com.melodiousplayer.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.melodiousplayer.util.Arith;
import com.melodiousplayer.util.IpUtils;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

/**
 * 服务器相关信息
 *
 * @author Mike
 * @date 2025/12/05
 */
public class Server {

    private static final int OSHI_WAIT_SECOND = 1000;

    /**
     * CPU相关信息
     */
    private Cpu cpu = new Cpu();

    /**
     * 內存相关信息
     */
    private Mem mem = new Mem();

    /**
     * JVM相关信息
     */
    private Jvm jvm = new Jvm();

    /**
     * 服务器相关信息
     */
    private Sys sys = new Sys();

    /**
     * 磁盘相关信息
     */
    private List<SysFile> sysFiles = new LinkedList<SysFile>();

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }

    public Jvm getJvm() {
        return jvm;
    }

    public void setJvm(Jvm jvm) {
        this.jvm = jvm;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<SysFile> getSysFiles() {
        return sysFiles;
    }

    public void setSysFiles(List<SysFile> sysFiles) {
        this.sysFiles = sysFiles;
    }

    public void copyTo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        setCpuInfo(hal.getProcessor());

        setMemInfo(hal.getMemory());

        setSysInfo();

        setJvmInfo();

        setSysFiles(si.getOperatingSystem());
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor) {
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.ordinal()] - prevTicks[TickType.NICE.ordinal()];
        long irq = ticks[TickType.IRQ.ordinal()] - prevTicks[TickType.IRQ.ordinal()];
        long softirq = ticks[TickType.SOFTIRQ.ordinal()] - prevTicks[TickType.SOFTIRQ.ordinal()];
        long steal = ticks[TickType.STEAL.ordinal()] - prevTicks[TickType.STEAL.ordinal()];
        long cSys = ticks[TickType.SYSTEM.ordinal()] - prevTicks[TickType.SYSTEM.ordinal()];
        long user = ticks[TickType.USER.ordinal()] - prevTicks[TickType.USER.ordinal()];
        long iowait = ticks[TickType.IOWAIT.ordinal()] - prevTicks[TickType.IOWAIT.ordinal()];
        long idle = ticks[TickType.IDLE.ordinal()] - prevTicks[TickType.IDLE.ordinal()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory) {
        mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        mem.setFree(memory.getAvailable());
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo() {
        Properties props = System.getProperties();
        sys.setComputerName(IpUtils.getHostName());
        sys.setComputerIp(IpUtils.getHostIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() {
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os) {
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysFile sysFile = new SysFile();
            sysFile.setDirName(fs.getMount());
            sysFile.setSysTypeName(fs.getType());
            sysFile.setTypeName(fs.getName());
            sysFile.setTotal(convertFileSize(total));
            sysFile.setFree(convertFileSize(free));
            sysFile.setUsed(convertFileSize(used));
            sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
            sysFiles.add(sysFile);
        }
    }

    /**
     * 字节转换
     *
     * @param size 字节大小
     * @return 转换后值
     */
    public String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }

    public void copyTermuxTo() {
        setTermuxCpuInfo();
        setTermuxMemInfo();
        setTermuxSysInfo();
        setTermuxJvmInfo();
        setTermuxSysFiles();
    }

    /**
     * 获取Termux系统下CPU的核心数
     *
     * @return CPU的核心数
     */
    private int getTermuxCPUCoreCount() {
        int count = 0;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"cat", "/proc/cpuinfo"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("processor")) {
                    count++;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获取Termux系统下CPU使用率
     *
     * @param keyword 需要查询的关键字
     * @return CPU使用率
     */
    private double getTermuxCPUUsage(String keyword) {
        double cpuUsage = 0;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"top", "-b", "-n", "1"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains("%" + keyword)) {
                    String[] info = line.split("\\s+");
                    for (String substring : info) {
                        if (substring.toLowerCase().contains(keyword)) {
                            Pattern pattern = Pattern.compile("\\d+");
                            Matcher matcher = pattern.matcher(substring);
                            if (matcher.find()) {
                                cpuUsage = Double.parseDouble(matcher.group());
                            }
                        }
                    }
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuUsage;
    }

    /**
     * 获取Termux系统下物理主机的运行内存大小
     *
     * @param keyword 需要查询的关键字
     * @return 物理主机的运行内存大小
     */
    private long getTermuxPhysicalMemorySize(String keyword) {
        long size = 0L;
        try {
            Process process = Runtime.getRuntime().exec("cat /proc/meminfo");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    String[] parts = line.split("\\s+");
                    // 总内存，单位KB
                    size = Long.parseLong(parts[1]);
                    // 找到所需信息后退出循环
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size * 1024;
    }

    /**
     * 获取Termux系统下的IP地址
     *
     * @return IP地址
     */
    private String getTermuxIp() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                // 过滤掉loopback接口
                if (networkInterface.isLoopback() || !networkInterface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // 过滤掉IPv6地址（可选）
                    if (addr instanceof java.net.Inet4Address) {
                        ip = addr.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    /**
     * 获取Termux系统下指定路径的文件系统名
     *
     * @param mount 盘符挂载路径
     * @return 文件系统名
     */
    private String getTermuxFileSystemName(String mount) {
        String fileSystemName = "";
        Path path = Paths.get(mount); // 获取根目录的路径，你可以修改为其他路径
        try {
            FileStore store = Files.getFileStore(path);
            fileSystemName = store.type();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystemName;
    }

    /**
     * 设置Termux系统下的CPU信息
     */
    private void setTermuxCpuInfo() {
        int coreCount = getTermuxCPUCoreCount();
        cpu.setCpuNum(coreCount);
        cpu.setTotal(getTermuxCPUUsage("cpu"));
        cpu.setSys(getTermuxCPUUsage("sys"));
        cpu.setUsed(getTermuxCPUUsage("user"));
        cpu.setWait(getTermuxCPUUsage("iow"));
        cpu.setFree(getTermuxCPUUsage("idle"));
    }

    /**
     * 设置设置Termux系统下的内存信息
     */
    private void setTermuxMemInfo() {
        mem.setTotal(getTermuxPhysicalMemorySize("MemTotal"));
        mem.setUsed(getTermuxPhysicalMemorySize("Active"));
        mem.setFree(getTermuxPhysicalMemorySize("MemAvailable"));
    }

    /**
     * 设置Termux系统下的服务器信息
     */
    private void setTermuxSysInfo() {
        Properties props = System.getProperties();
        sys.setComputerName(IpUtils.getHostName());
        sys.setComputerIp(getTermuxIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
    }

    /**
     * 设置Termux系统下的Java虚拟机
     */
    private void setTermuxJvmInfo() {
        Properties props = System.getProperties();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置Termux系统下的磁盘信息
     */
    private void setTermuxSysFiles() {
        if (!sysFiles.isEmpty()) {
            sysFiles.clear();
        }
        try {
            // 执行df -h命令
            Process process = Runtime.getRuntime().exec("df");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            // 跳过第一行（通常是标题行）
            reader.readLine();
            // 读取并打印剩余的行
            while ((line = reader.readLine()) != null) {
                String[] info = line.split("\\s+");
                long total = Long.parseLong(info[1]) * 1024;
                long used = Long.parseLong(info[2]) * 1024;
                long free = Long.parseLong(info[3]) * 1024;
                SysFile sysFile = new SysFile();
                String dirName = info[5];
                sysFile.setDirName(dirName);
                sysFile.setSysTypeName(getTermuxFileSystemName(dirName));
                sysFile.setTypeName(info[0]);
                sysFile.setTotal(convertFileSize(total));
                sysFile.setFree(convertFileSize(free));
                sysFile.setUsed(convertFileSize(used));
                sysFile.setUsage(Arith.mul(Arith.div(used, total, 4), 100));
                sysFiles.add(sysFile);
            }
            reader.close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
