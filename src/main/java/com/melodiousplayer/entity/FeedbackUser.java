package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName feedback_user
 */
@TableName(value = "feedback_user")
@Data
public class FeedbackUser implements Serializable {
    /**
     * 反馈创作者主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 反馈ID
     */
    @TableField(value = "feedback_id")
    private Integer feedbackId;

    /**
     * 创作者ID
     */
    @TableField(value = "user_id")
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}