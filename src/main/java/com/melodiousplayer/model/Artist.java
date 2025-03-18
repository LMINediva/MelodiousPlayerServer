package com.melodiousplayer.model;

/**
 * 艺术家
 *
 * @author Mike
 * @date 2025/03/18
 */
public class Artist {
    /**
     * 艺术家ID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String artistName;

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
     * getArtistName
     */
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName == null ? null : artistName.trim();
    }
}