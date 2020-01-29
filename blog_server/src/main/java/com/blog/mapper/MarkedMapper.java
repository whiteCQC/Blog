package com.blog.mapper;

import com.blog.model.Marked;
import com.blog.model.MarkedKey;

import java.util.List;

public interface MarkedMapper {
    int deleteByPrimaryKey(MarkedKey key);

    int deleteByName(Integer uid, String markName);

    int insert(Marked record);

    int updateByPrimaryKey(Marked record);

    List <Marked> selectByUid(Integer uid);

    Marked getMarkedArticles(Integer uid, Integer markId);

    int getMaxMarkId(Integer uid);

    int getMarkId(Integer uid, String markName);

}