package com.melodiousplayer.model;

/**
 * 首页数据项
 *
 * @author Mike
 * @date 2025/03/18
 */
public class HomeItem {
    /**
     * 视频ID
     */
    private Integer id;

    /**
     * 类型
     */
    private String type;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 海报图片
     */
    private String posterPic;

    /**
     * 缩略图
     */
    private String thumbnailPic;

    /**
     * 视频链接
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
     * 状态
     */
    private Integer status;

    /**
     * 追踪链接
     */
    private String traceUrl;

    /**
     * 点击跳转链接
     */
    private String clickUrl;

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
     * getType
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
     * getStatus
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * getTraceUrl
     */
    public String getTraceUrl() {
        return traceUrl;
    }

    public void setTraceUrl(String traceUrl) {
        this.traceUrl = traceUrl == null ? null : traceUrl.trim();
    }

    /**
     * getClickUrl
     */
    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl == null ? null : clickUrl.trim();
    }
}