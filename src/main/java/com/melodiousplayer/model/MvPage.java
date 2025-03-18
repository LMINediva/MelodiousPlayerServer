package com.melodiousplayer.model;

/**
 * MV数据项
 *
 * @author Mike
 * @date 2025/03/18
 */
public class MvPage {
    /**
     * MV页面ID
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