package com.blog.vo;

import com.blog.model.Marked;

public class MarkedMoveVo {
    private int uid;
    private int aid;
    private int oldMarkedId;
    private int newMarkedId;

    public MarkedMoveVo(){}

    public MarkedMoveVo(int uid, int aid, int oldMarkedId, int newMarkedId) {
        this.uid = uid;
        this.aid = aid;
        this.oldMarkedId = oldMarkedId;
        this.newMarkedId = newMarkedId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getOldMarkedId() {
        return oldMarkedId;
    }

    public void setOldMarkedId(int oldMarkedId) {
        this.oldMarkedId = oldMarkedId;
    }

    public int getNewMarkedId() {
        return newMarkedId;
    }

    public void setNewMarkedId(int newMarkedId) {
        this.newMarkedId = newMarkedId;
    }
}
