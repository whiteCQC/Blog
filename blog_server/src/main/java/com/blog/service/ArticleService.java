package com.blog.service;

import com.blog.model.Article;

import java.util.List;

public interface ArticleService {
    void createArticle(Article article);
    Article getArticleById(Integer aid);
    List<Article> getArticleByUser(Integer uid);



}
