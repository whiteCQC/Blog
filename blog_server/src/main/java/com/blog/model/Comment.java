package com.blog.model;

import java.util.Date;

public class Comment extends CommentKey {

    private Integer uid;

    private Integer commentOfCid;

    private String commentContent;

    private Date commentDate;

    @Override
    public String toString() {
        return "Comment{" +
                "uid=" + uid +
                ", commentOfCid=" + commentOfCid +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate=" + commentDate +
                ", aid=" + this.getAid() +
                ", cid=" + this.getCid() +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCommentOfCid() {
        return commentOfCid;
    }

    public void setCommentOfCid(Integer commentOfCid) {
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
