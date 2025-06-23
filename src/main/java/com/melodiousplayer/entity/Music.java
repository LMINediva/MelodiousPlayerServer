package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 悦单界面
 * @TableName music
 */
@TableName(value ="music")
@Data
public class Music implements Serializable {
    /**
     * 音乐ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 总数
     */
    @TableField(value = "total_count")
    private Integer totalCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}