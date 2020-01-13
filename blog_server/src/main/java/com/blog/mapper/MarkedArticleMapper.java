package com.blog.mapper;

import com.blog.model.MarkedArticleKey;

public interface MarkedArticleMapper {
    int deleteByPrimaryKey(MarkedArticleKey key);

    int insert(MarkedArticleKey record);

}