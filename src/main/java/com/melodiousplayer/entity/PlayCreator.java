package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName play_creator
 */
@TableName(value ="play_creator")
@Data
public class PlayCreator implements Serializable {
    /**
     * 音乐清单创作者主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 音乐清单ID
     */
    @TableField(value = "play_id")
    private Integer playId;

    /**
     * 创作者ID
     */
    @TableField(value = "creator_id")
    private Integer creatorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}