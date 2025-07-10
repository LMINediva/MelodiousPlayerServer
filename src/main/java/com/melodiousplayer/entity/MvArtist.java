package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName mv_artist
 */
@TableName(value ="mv_artist")
@Data
public class MvArtist implements Serializable {
    /**
     * MV艺术家ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * MVID
     */
    @TableField(value = "mv_id")
    private Integer mvId;

    /**
     * 艺术家ID
     */
    @TableField(value = "artist_id")
    private Integer artistId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}