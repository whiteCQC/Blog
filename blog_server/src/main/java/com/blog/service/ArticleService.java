package com.blog.service;

import com.blog.model.Article;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    void createArticle(Article article);

    Article getArticleById(Integer aid);

    List<Article> getArticleByUser(Integer uid, int pageNum);

    List<Article> getNewArticleByUser(Integer uid);

    void updateArticle(Article article);

    void deleteArticle(Integer aid);

    void updateViewNum(Integer aid);

    PageInfo<Article> getHotArticle(Integer pageNum);

    PageInfo<Article> getArticleByKeyword (String keyword, int pageNum);  //搜索返回不含具体内容的文章

    Map<String,Number> getArticleInfoByUser(Integer uid);



}
