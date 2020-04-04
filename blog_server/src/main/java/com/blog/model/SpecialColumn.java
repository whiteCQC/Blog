package com.blog.model;

import java.util.List;

public class SpecialColumn {
    private int spColId;

    private int uid;

    private String spColName;

    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public int getSpColId() {
        return spColId;
    }

    public void setSpColId(int spColId) {
        this.spColId = spColId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getSpColName() {
        return spColName;
    }

    public void setSpColName(String spColName) {
        this.spColName = spColName;
    }
}
