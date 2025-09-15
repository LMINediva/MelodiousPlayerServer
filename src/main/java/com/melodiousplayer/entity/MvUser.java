package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName mv_user
 */
@TableName(value = "mv_user")
@Data
public class MvUser implements Serializable {
    /**
     * MV创作者主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * MVID
     */
    @TableField(value = "mv_id")
    private Integer mvId;

    /**
     * 创作者ID
     */
    @TableField(value = "user_id")
    private Long userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}