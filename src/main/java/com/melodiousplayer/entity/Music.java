package com.melodiousplayer.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 首页在线音乐数据项
 *
 * @TableName music
 */
@TableName(value = "music")
@Data
public class Music implements Serializable {
    /**
     * 音乐ID
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
     * 艺术家姓名
     */
    @TableField(value = "artist_name")
    private String artistName;

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
     * 歌词
     */
    @TableField(value = "lyric")
    private String lyric;

    /**
     * 音乐链接
     */
    @TableField(value = "url")
    private String url;

    /**
     * 高品质音乐链接
     */
    @TableField(value = "hd_url")
    private String hdUrl;

    /**
     * 超高品质音乐链接
     */
    @TableField(value = "uhd_url")
    private String uhdUrl;

    /**
     * 音乐大小
     */
    @TableField(value = "music_size")
    private Double musicSize;

    /**
     * 高品质音乐大小
     */
    @TableField(value = "hd_music_size")
    private Double hdMusicSize;

    /**
     * 超高品质音乐大小
     */
    @TableField(value = "uhd_music_size")
    private Double uhdMusicSize;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 一首在线音乐对应的一位添加者ID（系统用户）
     */
    @TableField(exist = false)
    private SysUser sysUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}