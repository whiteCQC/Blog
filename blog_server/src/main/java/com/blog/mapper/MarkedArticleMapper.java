package com.blog.mapper;

import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.MarkedKey;

public interface MarkedArticleMapper {
    int deleteByPrimaryKey(MarkedArticle key);

    int insert(MarkedArticle record);

    //删除一个收藏夹内全部记录
    int deleteByMarked(MarkedKey markedKey);

}