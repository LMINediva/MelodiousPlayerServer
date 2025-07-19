package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 艺术家
 *
 * @TableName artist
 */
@TableName(value = "artist")
@Data
public class Artist implements Serializable {
    /**
     * 艺术家ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    @TableField(value = "artist_name")
    private String artistName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}