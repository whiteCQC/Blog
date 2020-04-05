package com.blog.vo;

import java.util.Date;

public class CommentVo {
    private int uid;

    private int commentOfCid;

    private String commentContent;

    private Date commentDate;

    private String userName;

    public CommentVo(int uid, int commentOfCid, String commentContent, Date commentDate, String userName) {
        this.uid = uid;
        this.commentOfCid = commentOfCid;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.userName = userName;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getCommentOfCid() {
        return commentOfCid;
    }

    public void setCommentOfCid(int commentOfCid) {
        this.commentOfCid = commentOfCid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
