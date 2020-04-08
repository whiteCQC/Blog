package com.blog.vo;

import java.util.Date;

public class CommentVo {
    private Integer uid;

    private Integer commentOfCid;

    private String commentContent;

    private Date commentDate;

    private String userName;

    public CommentVo(Integer uid, Integer commentOfCid, String commentContent, Date commentDate, String userName) {
        this.uid = uid;
        this.commentOfCid = commentOfCid;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "uid=" + uid +
                ", commentOfCid=" + commentOfCid +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate=" + commentDate +
                ", userName='" + userName + '\'' +
                '}';
    }
}
