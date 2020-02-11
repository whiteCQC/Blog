package com.blog.service;
import com.blog.model.User;
import com.blog.vo.Fan;

import java.util.List;

public interface UserService  {

    Boolean createUser(User user);

    User getUserById(Integer uid);

    User getUserByEmail(String email);

    void updateUser(User user);

    User getAuthor(Integer aid);

    Integer getFanNum(Integer uid);

    Boolean follow(Fan fan);

    Boolean cancelFollow(Fan fan);

    List<Fan> getFans(Integer uid);

    List<Fan> getConcerns(Integer uid);
}
