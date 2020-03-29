package com.blog.service.impl;


import com.blog.mapper.FollowerMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.Follower;
import com.blog.model.User;
import com.blog.service.UserService;
import com.blog.util.PasswordUtil;
import com.blog.vo.Fan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FollowerMapper followerMapper;


    @Override
    public Boolean createUser(User user) {
        User u = userMapper.selectByEmail(user.getEmail());
        if(u == null) {
            user.setPassword(PasswordUtil.toMD5(user.getPassword()));
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
        return userMapper.selectByEmail(email);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User getAuthor(Integer aid) {
        return userMapper.getAuthor(aid);
    }

    @Override
    public Integer getFanNum(Integer uid) {
        return followerMapper.getFanNum(uid);
    }

    @Override
    public Boolean follow(Fan fan) {
        Follower f = new Follower();
        f.setFollowerId(fan.getFollowerId());
        f.setUid(fan.getAuthorId());
        int i = followerMapper.insert(f);
        return i!=0;
    }

    @Override
    public Boolean cancelFollow(Fan fan) {
        Follower f = new Follower();
        f.setFollowerId(fan.getFollowerId());
        f.setUid(fan.getAuthorId());
        int i = followerMapper.deleteByPrimaryKey(f);
        return i!=0;
    }

    @Override
    public List<Fan> getFans(Integer uid) {
        return followerMapper.getFansOfUser(uid);
    }

    @Override
    public List<Fan> getConcerns(Integer uid) {
        return followerMapper.getConcernsOfUser(uid);
    }
}
