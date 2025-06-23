package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 创作者
 * @TableName creator
 */
@TableName(value ="creator")
@Data
public class Creator implements Serializable {
    /**
     * 创作者ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 小头像
     */
    @TableField(value = "small_avatar")
    private String smallAvatar;

    /**
     * 大头像
     */
    @TableField(value = "large_avatar")
    private String largeAvatar;

    /**
     * VIP等级
     */
    @TableField(value = "vip_level")
    private Integer vipLevel;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}