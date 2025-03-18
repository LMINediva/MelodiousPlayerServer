package com.melodiousplayer.model;

/**
 * 悦单界面
 *
 * @author Mike
 * @date 2025/03/18
 */
public class Music {
    /**
     * 音乐ID
     */
    private Integer id;

    /**
     * 总数
     */
    private Integer totalCount;

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
     * getTotalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}