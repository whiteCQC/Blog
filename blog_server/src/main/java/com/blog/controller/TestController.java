package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端测试Controller，不用管
 */

@RestController
public class TestController {

    @GetMapping("/user")
    public Result Start(){
        return Result.success(new User());
    }

    @PostMapping("/login")
    public Result GetsTest(@RequestBody User user){
        System.out.println(user.getUname());
        User u = new User();
        u.setUid(12);
        u.setUname("aaa");
        return Result.success(u);
    }

    @PostMapping("/send")
    public void Send(@RequestBody User user){
        System.out.println(user.getUname());
    }
}
