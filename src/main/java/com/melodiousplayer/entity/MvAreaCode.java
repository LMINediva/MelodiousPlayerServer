package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName mv_area_code
 */
@TableName(value = "mv_area_code")
@Data
public class MvAreaCode implements Serializable {
    /**
     * MV区域代码ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * MVID
     */
    @TableField(value = "mv_id")
    private Integer mvId;

    /**
     * MV区域ID
     */
    @TableField(value = "mv_area_id")
    private Integer mvAreaId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}