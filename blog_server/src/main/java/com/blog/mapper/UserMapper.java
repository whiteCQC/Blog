package com.blog.mapper;

import com.blog.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    User selectByPrimaryKey(Integer uid);

    User selectByEmail(String email);

    int updateByPrimaryKey(User record);
}