package com.blog.model;

import java.util.List;

public class Marked extends MarkedKey {

    private String markName;

    private List<Article> articleList;

    public Marked(Integer uid, Integer markId) {
        super(uid, markId);
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
}