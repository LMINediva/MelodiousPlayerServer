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
 * 反馈
 *
 * @TableName feedback
 */
@TableName(value = "feedback")
@Data
public class Feedback implements Serializable {
    /**
     * 反馈ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 反馈图片
     */
    @TableField(value = "picture")
    private String picture;

    /**
     * 提交时间
     */
    @TableField(value = "submission_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date submissionTime;

    /**
     * 一条反馈对应的一位添加者ID（系统用户）
     */
    @TableField(exist = false)
    private SysUser sysUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}