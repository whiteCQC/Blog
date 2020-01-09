package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/register")
    public Result register(){
        User user= new com.blog.model.User();
        user.setUname("aaa");
        user.setPassword("123456");
        user.setEmail("dxy@12345.com");
        user.setBirth(new Date());
        user.setGender("M");
        if(userService.createUser(user))
        {
            return Result.success();
        }
        else
        {
            return Result.error("邮箱已被使用");
        }

    }

    @PostMapping("/login")
    public Result userLogin(String email, String password)
    {
        com.blog.model.User user = userService.getUserByEmail(email);
        if(user == null)
        {
            return Result.error("用户不存在");
        }
        else
        {
            if(user.getPassword().equals(password))
            {
                HashMap<String , Object> map = new HashMap<>();
                map.put("uid", user.getUid());
                map.put("uname",user.getUname());
                return Result.success(map);
            }
            else
            {
                return Result.error("密码不正确");
            }
        }
    }
}
