package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    @GetMapping("/user")
    public Result Start(){
        return Result.success(new UserInfo(1,"张三"));
    }
}
