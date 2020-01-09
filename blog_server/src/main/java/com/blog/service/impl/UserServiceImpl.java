package com.blog.service.impl;


import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean createUser(User user) {
        User u = userMapper.selectByEmail(user.getEmail());
        if(u == null) {
            userMapper.insert(user);
            return true;
        }
        else
            return false;

    }

    @Override
    public User getUserById(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public User getUserByEmail(String email) {
        User user= userMapper.selectByEmail(email);
        return user;
    }
}
