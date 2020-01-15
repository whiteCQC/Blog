package com.blog.service;

import com.blog.model.Article;
import com.blog.model.Marked;

import java.util.List;

public interface MarkService {
    List<Marked> getMarkedListByUid(Integer uid);

    boolean  addMarked(Marked marked);

    List<Article> getMarkedArticles(Integer uid, Integer markId);

    void deleteMarked(Integer uid, String markName);

}
