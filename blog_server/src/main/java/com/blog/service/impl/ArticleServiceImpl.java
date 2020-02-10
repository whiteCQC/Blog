package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void createArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public Article getArticleById(Integer aid) {
        return articleMapper.selectByPrimaryKey(aid);
    }

    @Override
    public PageInfo<Article> getHotArticle(Integer pageNum) {
        List<Article> list = articleMapper.getAll();
        sortHot(list);
        PageMethod.startPage(pageNum, 5);
        return new PageInfo<>(list);

    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKeyWithBLOBs(article);
    }

    @Override
    public void deleteArticle(Integer aid) {
        articleMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public void updateViewNum(Integer aid) {
        articleMapper.updateViewNum(aid);
    }

    @Override
    public List<Article> getArticleByUser(Integer uid, int pageNum) {
        PageMethod.startPage(pageNum, 5);
        PageInfo<Article> pageInfo = new PageInfo<>(articleMapper.selectByUid(uid));
        return pageInfo.getList();
    }

    @Override
    public List<Article> getNewArticleByUser(Integer uid) {
        return articleMapper.getNewArticle(uid);
    }

    @Override
    public PageInfo<Article> getArticleByKeyword(String keyword, int pageNum) {
        PageMethod.startPage(pageNum, 5);
        return new PageInfo<>(articleMapper.selectByKeyword(keyword));
    }

    @Override
    public Map<String, Integer> getArticleInfoByUser(Integer uid) {
        return articleMapper.getArticleInfoByUid(uid);
    }

    private void sortHot(List<Article> list)
    {
        list.sort((o1, o2) -> {
            if (getScore(o1) > getScore(o2))
                return -1;
            else if (getScore(o1) == getScore(o2))
                return 0;
            else
                return 1;
        });
    }

    private int getScore(Article a)
    {
        Date date = a.getDate();
        int view = a.getViewNum();
        return view/((int)(new Date().getTime()-date.getTime()) / (1000 * 60 * 60 * 24)+1);
    }
}

