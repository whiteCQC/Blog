package com.blog.model;

public class CommentKey {
    private int aid;

    private int cid;


    public CommentKey(int aid, int cid) {
        this.aid = aid;
        this.cid = cid;
    }

    public CommentKey() {
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
