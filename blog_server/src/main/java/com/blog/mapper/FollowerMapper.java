package com.blog.mapper;

import com.blog.model.FollowerKey;

public interface FollowerMapper {
    int deleteByPrimaryKey(FollowerKey key);

    int insert(FollowerKey record);

}