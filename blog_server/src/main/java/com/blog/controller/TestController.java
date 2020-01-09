package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.UserInfo;
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
        return Result.success(new UserInfo(1,"张三"));
    }

    @PostMapping("/login")
    public Result GetsTest(@RequestBody UserInfo user){
        System.out.println(user.getName());
        return Result.success(new UserInfo(2,"李四"));
    }

    @PostMapping("/send")
    public void Send(@RequestBody UserInfo user){
        System.out.println(user.getName());
    }
}
