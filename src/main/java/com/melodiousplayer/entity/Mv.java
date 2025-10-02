package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * MV
 *
 * @TableName mv
 */
@TableName(value = "mv")
@Data
public class Mv implements Serializable {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 艺术家姓名
     */
    @TableField(value = "artist_name")
    private String artistName;

    /**
     * 海报
     */
    @TableField(value = "poster_pic")
    private String posterPic;

    /**
     * 缩略图
     */
    @TableField(value = "thumbnail_pic")
    private String thumbnailPic;

    /**
     * 发行时间
     */
    @TableField(value = "regdate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date regdate;

    /**
     * 视频源类型名
     */
    @TableField(value = "video_source_type_name")
    private String videoSourceTypeName;

    /**
     * 总浏览量
     */
    @TableField(value = "total_views")
    private Integer totalViews;

    /**
     * PC端浏览量
     */
    @TableField(value = "total_pc_views")
    private Integer totalPcViews;

    /**
     * 手机端浏览量
     */
    @TableField(value = "total_mobile_views")
    private Integer totalMobileViews;

    /**
     * 评论数
     */
    @TableField(value = "total_comments")
    private Integer totalComments;

    /**
     * 链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 高清视频链接
     */
    @TableField(value = "hd_url")
    private String hdUrl;

    /**
     * 超高清视频链接
     */
    @TableField(value = "uhd_url")
    private String uhdUrl;

    /**
     * 视频大小
     */
    @TableField(value = "video_size")
    private Double videoSize;

    /**
     * 高清视频大小
     */
    @TableField(value = "hd_video_size")
    private Double hdVideoSize;

    /**
     * 超高清视频大小
     */
    @TableField(value = "uhd_video_size")
    private Double uhdVideoSize;

    /**
     * 时长
     */
    @TableField(value = "duration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date duration;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 一部在线MV对应的一个区域
     */
    @TableField(exist = false)
    private MvArea mvArea;

    /**
     * 一部在线MV对应的一位添加者ID（系统用户）
     */
    @TableField(exist = false)
    private SysUser sysUser;

    /**
     * 旧的MV所属区域ID
     */
    @TableField(exist = false)
    private Integer oldMvAreaId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}