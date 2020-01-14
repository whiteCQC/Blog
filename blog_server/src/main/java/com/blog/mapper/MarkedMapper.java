package com.blog.mapper;

import com.blog.model.Marked;
import com.blog.model.MarkedKey;

import java.util.List;

public interface MarkedMapper {
    int deleteByPrimaryKey(MarkedKey key);

    int insert(Marked record);

    int updateByPrimaryKey(Marked record);

    List <Marked> selectByUid(Integer uid);

}