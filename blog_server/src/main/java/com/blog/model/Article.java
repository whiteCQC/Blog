package com.blog.model;

import java.util.Date;

public class Article {
    private Integer aid;

    private Integer uid;

    private String articleTitle;

    private Date date;

    private Integer type;

    private Integer mode;

    private Integer viewNum;

    private Integer spColId;

    private String articleContent;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", articleTitle='" + articleTitle + '\'' +
                ", date=" + date +
                ", type=" + type +
                ", mode=" + mode +
                ", viewNum=" + viewNum +
                ", spColId=" + spColId +
                ", articleContent='" + articleContent + '\'' +
                ", user=" + user +
                '}';
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getSpColId() {
        return spColId;
    }

    public void setSpColId(Integer spColId) {
        this.spColId = spColId;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }
}