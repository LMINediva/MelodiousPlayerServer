package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 音乐清单
 *
 * @TableName play
 */
@TableName(value = "play")
@Data
public class Play implements Serializable {
    /**
     * 音乐清单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 缩略图
     */
    @TableField(value = "thumbnail_pic")
    private String thumbnailPic;

    /**
     * 视频数量
     */
    @TableField(value = "video_count")
    private Integer videoCount;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 种类
     */
    @TableField(value = "category")
    private String category;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 浏览量
     */
    @TableField(value = "total_views")
    private Integer totalViews;

    /**
     * 点赞量
     */
    @TableField(value = "total_favorites")
    private Integer totalFavorites;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    /**
     * 积分
     */
    @TableField(value = "integral")
    private Integer integral;

    /**
     * 周积分
     */
    @TableField(value = "week_integral")
    private Integer weekIntegral;

    /**
     * 总积分
     */
    @TableField(value = "total_user")
    private Integer totalUser;

    /**
     * 排名
     */
    @TableField(value = "`rank`")
    private Integer rank;

    /**
     * 一条音乐清单对应的一位创作者ID（系统用户）
     */
    @TableField(exist = false)
    private SysUser sysUser;

    /**
     * 一条音乐清单对应的多部MV
     */
    @TableField(exist = false)
    private List<Mv> mvList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}