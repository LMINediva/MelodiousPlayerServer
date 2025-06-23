package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * MV数据项
 * @TableName mv_page
 */
@TableName(value ="mv_page")
@Data
public class MvPage implements Serializable {
    /**
     * MV页面ID
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