package com.blog.mapper;

import com.blog.model.Marked;
import com.blog.model.MarkedArticleKey;
import com.blog.model.MarkedKey;

public interface MarkedArticleMapper {
    int deleteByPrimaryKey(MarkedArticleKey key);

    int insert(MarkedArticleKey record);

    int deleteByMarked(MarkedKey markedKey);

}