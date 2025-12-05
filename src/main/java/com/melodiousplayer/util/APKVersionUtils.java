package com.melodiousplayer.util;

/**
 * APK版本检查工具类
 *
 * @author Mike
 * @date 2025/10/25
 */
public class APKVersionUtils {

    /**
     * 比较APK版本号的大小
     *
     * @param currentVersion 当前APK版本号
     * @param latestVersion  数据库中APK的最新版本号
     * @return 前者大则返回一个正数，后者大返回一个负数，相等则返回0
     * @throws Exception 比较异常
     */
    public static int compareVersion(String currentVersion, String latestVersion) throws Exception {
        if (currentVersion == null || latestVersion == null) {
            throw new Exception("APK版本比较错误：参数不能为空");
        }
        String[] versionArray1 = currentVersion.split("\\.");
        String[] versionArray2 = latestVersion.split("\\.");
        int idx = 0;
        // 取最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int different = 0;
        // 先比较长度，再比较字符
        while (idx < minLength && (different = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (different = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大
        different = (different != 0) ? different : versionArray1.length - versionArray2.length;
        return different;
    }

    /**
     * APK强制更新类型转换
     *
     * @param force APK强制更新参数
     * @return false不为强制更新，true为强制更新
     */
    public static boolean getConstraintValue(Integer force) {
        if (force == 0) {
            return false;
        } else return force == 1;
    }

}
