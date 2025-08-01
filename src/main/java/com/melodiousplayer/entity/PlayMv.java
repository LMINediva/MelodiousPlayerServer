package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName play_mv
 */
@TableName(value = "play_mv")
@Data
public class PlayMv implements Serializable {
    /**
     * 音乐清单MV主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 音乐清单ID
     */
    @TableField(value = "play_id")
    private Integer playId;

    /**
     * MVID
     */
    @TableField(value = "mv_id")
    private Integer mvId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}