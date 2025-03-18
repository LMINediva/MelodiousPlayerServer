package com.melodiousplayer.model;

import java.util.Date;

/**
 * 音乐清单
 *
 * @author Mike
 * @date 2025/03/18
 */
public class Play {
    /**
     * 音乐清单ID
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 缩略图
     */
    private String thumbnailPic;

    /**
     * 图片
     */
    private String playListPic;

    /**
     * 大图片
     */
    private String playListBigPic;

    /**
     * 视频数量
     */
    private Integer videoCount;

    /**
     * 描述
     */
    private String description;

    /**
     * 种类
     */
    private String category;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 浏览量
     */
    private Integer totalViews;

    /**
     * 点赞量
     */
    private Integer totalFavorites;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 积分
     */
    private Integer integral;

    /**
     * 周积分
     */
    private Integer weekIntegral;

    /**
     * 总积分
     */
    private Integer totalUser;

    /**
     * 排名
     */
    private Integer rank;

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
     * getThumbnailPic
     */
    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic == null ? null : thumbnailPic.trim();
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

    /**
     * getPlayListBigPic
     */
    public String getPlayListBigPic() {
        return playListBigPic;
    }

    public void setPlayListBigPic(String playListBigPic) {
        this.playListBigPic = playListBigPic == null ? null : playListBigPic.trim();
    }

    /**
     * getVideoCount
     */
    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
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
     * getCategory
     */
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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
     * getTotalViews
     */
    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    /**
     * getTotalFavorites
     */
    public Integer getTotalFavorites() {
        return totalFavorites;
    }

    public void setTotalFavorites(Integer totalFavorites) {
        this.totalFavorites = totalFavorites;
    }

    /**
     * getUpdateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * getCreatedTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * getIntegral
     */
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * getWeekIntegral
     */
    public Integer getWeekIntegral() {
        return weekIntegral;
    }

    public void setWeekIntegral(Integer weekIntegral) {
        this.weekIntegral = weekIntegral;
    }

    /**
     * getTotalUser
     */
    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    /**
     * getRank
     */
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}