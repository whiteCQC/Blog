package com.blog.vo;

import com.blog.model.Article;

import java.util.List;

public class Author {
    private Integer uid;
    private String name;
    private Integer fanNum;
    private Integer articleNum;
    private Integer totalView;
    private List<Article> newArticles;

    public Author(Integer uid, String name, Integer fanNum, Integer articleNum, Integer totalView, List<Article> newArticles) {
        this.uid = uid;
        this.name = name;
        this.fanNum = fanNum;
        this.articleNum = articleNum;
        this.totalView = totalView;
        this.newArticles = newArticles;
    }
    public  Author(){}

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFanNum() {
        return fanNum;
    }

    public void setFanNum(Integer fanNum) {
        this.fanNum = fanNum;
    }

    public Integer getArticleNum() {
        return articleNum;
    }

    public void setArticleNum(Integer articleNum) {
        this.articleNum = articleNum;
    }

    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }

    public List<Article> getNewArticles() {
        return newArticles;
    }

    public void setNewArticles(List<Article> newArticles) {
        this.newArticles = newArticles;
    }
}
