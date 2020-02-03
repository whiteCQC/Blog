package com.blog.mapper;

import com.blog.model.Follower;
import com.blog.model.FollowerKey;

public interface FollowerMapper {
    int deleteByPrimaryKey(FollowerKey key);

    int insert(FollowerKey record);

    Follower selectByUid(Integer uid);

    int getFanNum(Integer uid);

}