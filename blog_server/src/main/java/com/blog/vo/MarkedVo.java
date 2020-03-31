package com.blog.vo;

/**
 *  个人收藏夹
 */
public class MarkedVo {

    private Integer uid;

    private String markName;

    private Integer markId;

    public MarkedVo(Integer uid, String markName, Integer markId) {
        this.uid = uid;
        this.markName = markName;
        this.markId = markId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }
}
