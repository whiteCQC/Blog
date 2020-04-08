package com.blog.model;

public class CommentKey {
    private Integer aid;

    private Integer cid;


    public CommentKey(Integer aid, Integer cid) {
        this.aid = aid;
        this.cid = cid;
    }

    public CommentKey() {
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
