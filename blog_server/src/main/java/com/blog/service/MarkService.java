package com.blog.service;

import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;

import java.util.List;

public interface MarkService {
    List<Marked> getMarkedListByUid(Integer uid);//得到用户的全部收藏夹

    boolean  addMarked(Marked marked);//新建收藏夹

    List<Article> getMarkedArticles(Integer uid, Integer markId);//的带收藏夹内全部文章信息

    void deleteMarked(Integer uid, String markName);//删除一个收藏夹

    boolean addMarkedArticle(MarkedArticle markedArticle);

    void deleteMarkedArticle(MarkedArticle markedArticle);

}
