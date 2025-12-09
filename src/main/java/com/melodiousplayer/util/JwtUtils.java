package com.melodiousplayer.util;

import com.melodiousplayer.common.constant.JwtConstant;
import com.melodiousplayer.entity.CheckResult;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Date;

/**
 * jwt加密和解密的工具类
 *
 * @author java1234_小锋
 * @site www.java1234.com
 * @company Java知识分享网
 * @create 2019-08-13 上午 10:06
 */
public class JwtUtils {

    /**
     * 签发JWT
     *
     * @param id
     * @param subject   可以是JSON数据 尽可能少
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("Java1234")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 生成jwt token
     *
     * @param username
     * @return
     */
    public static String genJwtToken(String username) {
        return createJWT(username, username, 60 * 60 * 1000);
    }

    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) {
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 生成加密Key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(JwtConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析JWT字符串
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static double getLinuxCPUUsage() {
        double cpuUsage = 0;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"top", "-b", "-n", "1"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("%CPU")) {
                    System.out.println("CPU Usage: " + line);
                    cpuUsage = Double.parseDouble(line);
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuUsage;
    }

    public static double getCPUUsage() {
        double cpuUsage = 0;
        try {
            // 执行命令获取CPU使用情况
            Process process = Runtime.getRuntime().exec("cat /proc/stat");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("cpu")) {
                    // 解析CPU使用情况
                    String[] tokens = line.split("\\s+");
                    long user = Long.parseLong(tokens[1]) + Long.parseLong(tokens[2]) + Long.parseLong(tokens[3]);
                    long total = Long.parseLong(tokens[1]) + Long.parseLong(tokens[2]) + Long.parseLong(tokens[3]) + Long.parseLong(tokens[4]) + Long.parseLong(tokens[5]);
                    long idle = Long.parseLong(tokens[4]);
                    cpuUsage = 100.0 * (total - idle) / total;
                    System.out.println("CPU Usage: " + String.format("%.2f", cpuUsage) + "%");
                    break; // 只处理第一行（通常是最新的CPU状态）
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuUsage;
    }

    public static void main(String[] args) throws InterruptedException {
        //小明失效 10s
        /*String sc = createJWT("1", "小明", 60 * 60 * 1000);
        System.out.println(sc);
        System.out.println(validateJWT(sc).getErrCode());
        System.out.println(validateJWT(sc).getClaims().getId());
        System.out.println(validateJWT(sc).getClaims().getSubject());
        //Thread.sleep(3000);
        System.out.println(validateJWT(sc).getClaims());
        Claims claims = validateJWT(sc).getClaims();
        String sc2 = createJWT(claims.getId(), claims.getSubject(), JwtConstant.JWT_TTL);
        System.out.println(sc2);*/

        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory(); // 获取Java虚拟机中的总内存
        long freeMemory = runtime.freeMemory(); // 获取剩余空闲内存
        long maxMemory = runtime.maxMemory(); // 获取最大可用内存

        System.out.println("Total Memory: " + totalMemory / (1024 * 1024) + " MB");
        System.out.println("Free Memory: " + freeMemory / (1024 * 1024) + " MB");
        System.out.println("Max Memory: " + maxMemory / (1024 * 1024) + " MB");
    }

}
