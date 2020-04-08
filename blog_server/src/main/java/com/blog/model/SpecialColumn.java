package com.blog.model;

import java.util.List;

public class SpecialColumn {
    private Integer spColId;

    private Integer uid;

    private String spColName;

    private List<Article> articleList;

    @Override
    public String toString() {
        return "SpecialColumn{" +
                "spColId=" + spColId +
                ", uid=" + uid +
                ", spColName='" + spColName + '\'' +
                '}';
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public Integer getSpColId() {
        return spColId;
    }

    public void setSpColId(Integer spColId) {
        this.spColId = spColId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSpColName() {
        return spColName;
    }

    public void setSpColName(String spColName) {
        this.spColName = spColName;
    }
}
