package com.blog.service;

import com.blog.mapper.UserMapper;
import com.blog.model.User;

public interface UserService  {

    Boolean createUser(User user);
    User getUserById(Integer uid);
    User getUserByEmail(String email);
    void updateUser(User user);
    User getAuthor(Integer aid);
    Integer getFanNum(Integer uid);
}
