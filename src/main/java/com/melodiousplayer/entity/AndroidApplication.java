package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 安卓应用
 *
 * @TableName android_application
 */
@TableName(value = "android_application")
@Data
public class AndroidApplication implements Serializable {
    /**
     * 安卓应用ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 安卓应用名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 安卓应用图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 安卓应用版本号
     */
    @TableField(value = "version")
    private String version;

    /**
     * 安卓应用链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 安卓应用大小
     */
    @TableField(value = "size")
    private Double size;

    /**
     * 上传时间
     */
    @TableField(value = "upload_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date uploadTime;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}