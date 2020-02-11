package com.blog.mapper;

import com.blog.model.Follower;
import com.blog.vo.Fan;

import java.util.List;

public interface FollowerMapper {
    int deleteByPrimaryKey(Follower key);

    int insert(Follower record);

    List<Fan> getFansOfUser(Integer uid);//得到用户的粉丝

    List<Fan> getConcernsOfUser(Integer uid);//得到用户关注列表

    int getFanNum(Integer uid);

}