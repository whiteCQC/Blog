package com.blog.service.impl;

import com.blog.mapper.ArticleMapper;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        Article article = articleMapper.selectByPrimaryKey(aid);
        return article;
    }

    @Override
    public List<Article> getHotArticle() {
        List<Article> list = articleMapper.getAll();
        sortHot(list);
        return list;
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateByPrimaryKey(article);
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
        PageHelper.startPage(pageNum, 10);
        PageInfo<Article> pageInfo = new PageInfo<>(articleMapper.selectByUid(uid));
        List<Article> list = pageInfo.getList();
        return list;
    }

    @Override
    public PageInfo<Article> getArticleByKeyword(String keyword, int pageNum) {
        PageHelper.startPage(pageNum, 5);
        PageInfo<Article> pageInfo = new PageInfo<>(articleMapper.selectByKeyword(keyword));
        return pageInfo;
    }

    private List<Article> sortHot(List<Article> list)
    {
        Collections.sort(list, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                if (getScore(o1) > getScore(o2))
                    return -1;
                else if(getScore(o1) == getScore(o2))
                    return 0;
                else
                    return 1;


            }
        });
        return list;
    }

    private int getScore(Article a)
    {
        Date date = a.getDate();
        int view = a.getViewNum();
        return view/((int)(new Date().getTime()-date.getTime()) / (1000 * 60 * 60 * 24)+1);
    }
}

