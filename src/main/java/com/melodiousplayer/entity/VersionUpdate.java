package com.melodiousplayer.entity;

import lombok.Data;

/**
 * 安卓版本更新返回的json对象
 *
 * @author Mike
 * @date 2025/10/25
 */
@Data
public class VersionUpdate {

    // APK名
    private String name;
    // 更新标识
    private String update;
    // 新版本
    private String new_version;
    // 新版APK下载地址
    private String apk_file_url;
    // 更新提示信息
    private String update_log;
    // 新版APK大小
    private String target_size;
    // 是否强制更新
    private boolean constraint;

}
