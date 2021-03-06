package com.blog.model;

import java.util.List;

public class Marked extends MarkedKey {

    private String markName;

    private List<Article> articleList;

    public Marked(Integer uid, Integer markId, String markName) {
        super(uid, markId);
        this.markName = markName;
    }

    public Marked() {
        super();
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public String getMarkName() {
        return markName;
    }

    public void setMarkName(String markName) {
        this.markName = markName;
    }

    @Override
    public String toString() {
        return "Marked{" +
                "markName='" + markName + '\'' +
                ", markid=" + this.getMarkId() +
                ", uid=" + this.getUid() +
                '}';
    }
}