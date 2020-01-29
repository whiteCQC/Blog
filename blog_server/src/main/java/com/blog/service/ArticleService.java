package com.blog.service;

import com.blog.model.Article;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArticleService {

    void createArticle(Article article);

    Article getArticleById(Integer aid);

    List<Article> getArticleByUser(Integer uid, int pageNum);

    void updateArticle(Article article);

    void deleteArticle(Integer aid);

    void updateViewNum(Integer aid);

    List<Article> getHotArticle();

    PageInfo<Article> getArticleByKeyword (String keyword, int pageNum);  //搜索返回不含具体内容的文章



}
