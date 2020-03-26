package com.blog.model;

import java.util.Date;

public class Comment extends CommentKey {

    private int uid;

    private int commentOfCid;

    private String commentContent;

    private Date commentDate;


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
}
