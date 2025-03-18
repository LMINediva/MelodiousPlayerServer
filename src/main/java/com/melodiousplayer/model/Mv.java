package com.melodiousplayer.model;

import java.util.Date;

/**
 * MV
 *
 * @author Mike
 * @date 2025/03/18
 */
public class Mv {
    /**
     * ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 艺术家姓名
     */
    private String artistName;

    /**
     * 海报
     */
    private String posterPic;

    /**
     * 缩略图
     */
    private String thumbnailPic;

    /**
     * 专辑图片
     */
    private String albumImg;

    /**
     * 发行时间
     */
    private Date regdate;

    /**
     * 视频源类型名
     */
    private String videoSourceTypeName;

    /**
     * 总浏览量
     */
    private Integer totalViews;

    /**
     * PC端浏览量
     */
    private Integer totalPcViews;

    /**
     * 手机端浏览量
     */
    private Integer totalMobileViews;

    /**
     * 评论数
     */
    private Integer totalComments;

    /**
     * 链接
     */
    private String url;

    /**
     * 高清视频链接
     */
    private String hdUrl;

    /**
     * 超高清视频链接
     */
    private String uhdUrl;

    /**
     * 极高清视频链接
     */
    private String shdUrl;

    /**
     * 视频大小
     */
    private Integer videoSize;

    /**
     * 高清视频大小
     */
    private Integer hdVideoSize;

    /**
     * 超高清视频大小
     */
    private Integer uhdVideoSize;

    /**
     * 极高清视频大小
     */
    private Integer shdVideoSize;

    /**
     * 时长
     */
    private Integer duration;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 链接ID
     */
    private Integer linkId;

    /**
     * 视频列表图片
     */
    private String playListPic;

    /**
     * getId
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * getTitle
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * getDescription
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * getArtistName
     */
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName == null ? null : artistName.trim();
    }

    /**
     * getPosterPic
     */
    public String getPosterPic() {
        return posterPic;
    }

    public void setPosterPic(String posterPic) {
        this.posterPic = posterPic == null ? null : posterPic.trim();
    }

    /**
     * getThumbnailPic
     */
    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic == null ? null : thumbnailPic.trim();
    }

    /**
     * getAlbumImg
     */
    public String getAlbumImg() {
        return albumImg;
    }

    public void setAlbumImg(String albumImg) {
        this.albumImg = albumImg == null ? null : albumImg.trim();
    }

    /**
     * getRegdate
     */
    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    /**
     * getVideoSourceTypeName
     */
    public String getVideoSourceTypeName() {
        return videoSourceTypeName;
    }

    public void setVideoSourceTypeName(String videoSourceTypeName) {
        this.videoSourceTypeName = videoSourceTypeName == null ? null : videoSourceTypeName.trim();
    }

    /**
     * getTotalViews
     */
    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    /**
     * getTotalPcViews
     */
    public Integer getTotalPcViews() {
        return totalPcViews;
    }

    public void setTotalPcViews(Integer totalPcViews) {
        this.totalPcViews = totalPcViews;
    }

    /**
     * getTotalMobileViews
     */
    public Integer getTotalMobileViews() {
        return totalMobileViews;
    }

    public void setTotalMobileViews(Integer totalMobileViews) {
        this.totalMobileViews = totalMobileViews;
    }

    /**
     * getTotalComments
     */
    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    /**
     * getUrl
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * getHdUrl
     */
    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl == null ? null : hdUrl.trim();
    }

    /**
     * getUhdUrl
     */
    public String getUhdUrl() {
        return uhdUrl;
    }

    public void setUhdUrl(String uhdUrl) {
        this.uhdUrl = uhdUrl == null ? null : uhdUrl.trim();
    }

    /**
     * getShdUrl
     */
    public String getShdUrl() {
        return shdUrl;
    }

    public void setShdUrl(String shdUrl) {
        this.shdUrl = shdUrl == null ? null : shdUrl.trim();
    }

    /**
     * getVideoSize
     */
    public Integer getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(Integer videoSize) {
        this.videoSize = videoSize;
    }

    /**
     * getHdVideoSize
     */
    public Integer getHdVideoSize() {
        return hdVideoSize;
    }

    public void setHdVideoSize(Integer hdVideoSize) {
        this.hdVideoSize = hdVideoSize;
    }

    /**
     * getUhdVideoSize
     */
    public Integer getUhdVideoSize() {
        return uhdVideoSize;
    }

    public void setUhdVideoSize(Integer uhdVideoSize) {
        this.uhdVideoSize = uhdVideoSize;
    }

    /**
     * getShdVideoSize
     */
    public Integer getShdVideoSize() {
        return shdVideoSize;
    }

    public void setShdVideoSize(Integer shdVideoSize) {
        this.shdVideoSize = shdVideoSize;
    }

    /**
     * getDuration
     */
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * getStatus
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * getLinkId
     */
    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    /**
     * getPlayListPic
     */
    public String getPlayListPic() {
        return playListPic;
    }

    public void setPlayListPic(String playListPic) {
        this.playListPic = playListPic == null ? null : playListPic.trim();
    }
}