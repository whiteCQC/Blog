package com.blog.mapper;

import com.blog.model.Marked;
import com.blog.model.MarkedKey;
import com.blog.vo.MarkedMoveVo;

import java.util.List;

public interface MarkedMapper {
    int deleteByPrimaryKey(MarkedKey key);

    int deleteByName(Integer uid, Integer markId);

    int insert(Marked record);

    int updateByPrimaryKey(Marked record);

    List <Marked> selectByUid(Integer uid);

    Marked getMarkedArticles(Integer uid, Integer markId);

    Marked getMarked(Integer uid, String markName);

    int moveMarkedArticle(MarkedMoveVo markedMoveVo);

    int getMaxMarkId(Integer uid);

    int getMarkId(Integer uid, String markName);

}