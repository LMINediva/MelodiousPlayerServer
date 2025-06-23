package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 首页数据项
 * @TableName home_item
 */
@TableName(value ="home_item")
@Data
public class HomeItem implements Serializable {
    /**
     * 视频ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    @TableField(value = "type")
    private String type;

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
     * 海报图片
     */
    @TableField(value = "poster_pic")
    private String posterPic;

    /**
     * 缩略图
     */
    @TableField(value = "thumbnail_pic")
    private String thumbnailPic;

    /**
     * 视频链接
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
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 追踪链接
     */
    @TableField(value = "trace_url")
    private String traceUrl;

    /**
     * 点击跳转链接
     */
    @TableField(value = "click_url")
    private String clickUrl;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}