package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.User;
import com.blog.vo.Fan;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 前端测试Controller，不用管
 */

@RestController
public class UserTestController {

    @GetMapping("/user")
    public Result Start(){
        User u=new User();
        u.setUname("666a");
        u.setUid(41);
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



    @PostMapping("/loginTest")
    public Result userLogin(@RequestBody User userInfo)
    {
        System.out.println(userInfo.getEmail());
        System.out.println(userInfo.getPassword());

        HashMap < String, Object > map = new HashMap<>();
        map.put("uid", "10");
        map.put("uname","萝卜青菜");
        map.put("token",null);
        return Result.success(map);
    }
    @PostMapping("/loginTest2")
    public Result userLogin2(@RequestBody User userInfo)
    {
        return Result.error("密码错误");
    }

    @PostMapping("/registerTest")
    public Result register(@RequestBody User user){
        HashMap < String, Object > map = new HashMap<>();
        map.put("uid", "11");
        map.put("uname","十一");
        map.put("token",null);
        return Result.success(map);
    }

    @PostMapping("/userInfoTest")
    public Result GetUserInfo(@RequestBody User userInfo){
        User u=new User();
        u.setUid(1);
        u.setUname("萝卜青菜");
        u.setBirth(new Date());
        u.setEmail("666666@163.com");
        u.setGender("未知");

        return Result.success(u);


    }

}
