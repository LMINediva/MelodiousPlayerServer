package com.melodiousplayer.model;

/**
 * 创作者
 *
 * @author Mike
 * @date 2025/03/18
 */
public class Creator {
    /**
     * 创作者ID
     */
    private Integer id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 小头像
     */
    private String smallAvatar;

    /**
     * 大头像
     */
    private String largeAvatar;

    /**
     * VIP等级
     */
    private Integer vipLevel;

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
     * getNickName
     */
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * getSmallAvatar
     */
    public String getSmallAvatar() {
        return smallAvatar;
    }

    public void setSmallAvatar(String smallAvatar) {
        this.smallAvatar = smallAvatar == null ? null : smallAvatar.trim();
    }

    /**
     * getLargeAvatar
     */
    public String getLargeAvatar() {
        return largeAvatar;
    }

    public void setLargeAvatar(String largeAvatar) {
        this.largeAvatar = largeAvatar == null ? null : largeAvatar.trim();
    }

    /**
     * getVipLevel
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }
}