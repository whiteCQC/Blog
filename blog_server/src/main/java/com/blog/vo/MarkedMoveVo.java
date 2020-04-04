package com.blog.vo;

public class MarkedMoveVo {
    private int uid;
    private int aid;
    private int oldMarkedId;
    private int newMarkedId;

    public MarkedMoveVo(int uid, int aid, int oldMarkedId, int newMarkedId) {
        this.uid = uid;
        this.aid = aid;
        this.oldMarkedId = oldMarkedId;
        this.newMarkedId = newMarkedId;
    }

    public int getUid() {
        return uid;
    }

    public int getAid() {
        return aid;
    }

    public int getOldMarkedId() {
        return oldMarkedId;
    }

    public int getNewMarkedId() {
        return newMarkedId;
    }
}
