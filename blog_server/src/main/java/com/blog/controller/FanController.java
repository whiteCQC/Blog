package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.vo.Fan;
import org.springframework.web.bind.annotation.*;

@RestController
public class FanController {

    /**
     * 关注博主,传入{用户ID和被关注博主的ID}
     * @return 成功与否
     */
    @PostMapping("/FanAdd")
    public Result FanFollow(@RequestBody Fan fan){
        //TODO
        return null;
    }

    /**
     * 取消关注,传入{用户ID和被关注博主的ID}
     * @param fan
     * @return 成功与否
     */
    @PostMapping("/FanCancel")
    public Result CancelFollow(@RequestBody Fan fan){
        //TODO

        return null;
    }

    /**
     *
     * @return 对应用户的所拥有的粉丝基本信息{每个粉丝的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewFans")
    public Result ViewFans(@RequestParam(value = "uid") int userId){
        //TODO
        return null;
    }

    /**
     *
     * @return 对应用户的所关注的用户信息{每个用户的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewConcerns")
    public Result ViewConcerns(@RequestParam(value = "uid") int userId){
        //TODO
        return null;
    }
}
