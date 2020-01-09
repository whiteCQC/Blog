package com.blog.service;

import com.blog.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {

    void createArticle(Article article);
    Article getArticleById(Integer aid);
    List<Article> getArticleByUser(Integer uid);
    void updateArticle(Article article);
    void deleteArticle(Integer aid);
    void updateViewNum(Integer aid);
    List<Article> getArticleByKeyword (String keyword);



}
