package com.blog.controller;

import com.blog.bean.Result;
import com.blog.service.UserService;
import com.blog.vo.Fan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class FanController {
    @Autowired
    UserService userService;

    /**
     * 关注博主,传入{用户ID和被关注博主的ID}
     * @return 成功与否
     */
    @PostMapping("/FanAdd")
    public Result FanFollow(@RequestBody Fan fan){
        if(userService.follow(fan))
            return Result.success();
        else
            return Result.error("关注失败");
    }

    /**
     * 取消关注,传入{用户ID和被关注博主的ID}
     * @param fan
     * @return 成功与否
     */
    @PostMapping("/FanCancel")
    public Result CancelFollow(@RequestBody Fan fan){
        if(userService.cancelFollow(fan))
            return Result.success();
        else
            return Result.error("取消失败");
    }

    /**
     *
     * @return 对应用户的所拥有的粉丝基本信息{每个粉丝的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewFans")
    public Result ViewFans(@RequestParam(value = "uid") int userId){
        //fan里面只有followerName和followId有信息
        List<Fan> fans = userService.getFans(userId);
        HashMap< String, Object > map = new HashMap<>();
        map.put("fans",fans);
        return Result.success(map);
    }

    /**
     *
     * @return 对应用户的所关注的用户信息{每个用户的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewConcerns")
    public Result ViewConcerns(@RequestParam(value = "uid") int userId){
        //fan里面只有authorName和followedId有信息
        List<Fan> fans = userService.getConcerns(userId);
        HashMap< String, Object > map = new HashMap<>();
        map.put("fans",fans);
        return Result.success(map);
    }
}
