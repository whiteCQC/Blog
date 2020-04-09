package com.blog.service;

import com.blog.model.Article;
import com.blog.model.SpecialColumn;

import java.util.List;

public interface SpecialColumnService {
    List<SpecialColumn> getSpecialColumnsByUid(Integer uid);//得到用户的专栏

    List<Article> getSPArticles(Integer spColId);//得到一个专栏内的文章
}
