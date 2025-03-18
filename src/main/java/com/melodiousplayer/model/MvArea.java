package com.melodiousplayer.model;

/**
 * MV区域
 *
 * @author Mike
 * @date 2025/03/18
 */
public class MvArea {
    /**
     * MV地区ID
     */
    private Integer id;

    /**
     * 地区名
     */
    private String name;

    /**
     * 代码
     */
    private String code;

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
     * getName
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * getCode
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}