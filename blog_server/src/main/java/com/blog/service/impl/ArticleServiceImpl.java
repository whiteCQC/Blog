package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Override
    public void createArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public Article getArticleById(Integer aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        return article;
    }

    @Override
    public List<Article> getArticleByUser(Integer uid) {
        List<Article> list = articleMapper.selectByUid(uid);
        return list;
    }
}
