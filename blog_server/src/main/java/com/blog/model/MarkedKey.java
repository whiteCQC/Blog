package com.blog.model;

public class MarkedKey {
    private Integer uid;

    private Integer markId;

    public MarkedKey(Integer uid, Integer markId) {
        this.uid = uid;
        this.markId = markId;
    }

    public MarkedKey() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getMarkId() {
        return markId;
    }

    public void setMarkId(Integer markId) {
        this.markId = markId;
    }
}