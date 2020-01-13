package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * 前端测试Controller，不用管
 */

@RestController
public class TestController {

    @GetMapping("/user")
    public Result Start(){
        User u=new User();
        u.setUname("666a");
        u.setUid(41);
        return Result.success(u);
    }

    @GetMapping("/loginTest")
    public Result GetsTest(@RequestParam(value = "uid")int uid,@RequestParam(value="uname")String uname){
        System.out.println(uname);

        User u = new User();
        u.setUid(12);
        u.setUname("aaa");
        return Result.success(u);
    }

    @PostMapping("/send")
    public Result Send(@RequestBody User user){
        System.out.println(user.getUname());
        User u = new User();
        u.setUid(12);
        u.setUname("aaa");
        return Result.success(u);
    }
}
