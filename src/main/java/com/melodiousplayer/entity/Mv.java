package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * MV
 * @TableName mv
 */
@TableName(value ="mv")
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
     * 专辑图片
     */
    @TableField(value = "album_img")
    private String albumImg;

    /**
     * 发行时间
     */
    @TableField(value = "regdate")
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
     * 极高清视频链接
     */
    @TableField(value = "shd_url")
    private String shdUrl;

    /**
     * 视频大小
     */
    @TableField(value = "video_size")
    private Integer videoSize;

    /**
     * 高清视频大小
     */
    @TableField(value = "hd_video_size")
    private Integer hdVideoSize;

    /**
     * 超高清视频大小
     */
    @TableField(value = "uhd_video_size")
    private Integer uhdVideoSize;

    /**
     * 极高清视频大小
     */
    @TableField(value = "shd_video_size")
    private Integer shdVideoSize;

    /**
     * 时长
     */
    @TableField(value = "duration")
    private Integer duration;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 链接ID
     */
    @TableField(value = "link_id")
    private Integer linkId;

    /**
     * 视频列表图片
     */
    @TableField(value = "play_list_pic")
    private String playListPic;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}