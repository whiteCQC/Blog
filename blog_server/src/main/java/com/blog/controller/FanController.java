package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.vo.Fan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanController {

    /**
     *
     * @return 关注博主
     */
    @PostMapping("/FanAdd")
    public Result FanFollow(@RequestBody Fan fan){
        //TODO
        return null;
    }

    /**
     *
     * @param fan
     * @return 取消关注
     */
    @PostMapping("/FanCancel")
    public Result CancelFollow(@RequestBody Fan fan){
        //TODO

        return null;
    }

    /**
     *
     * @return 对应用户的粉丝基本信息
     */
    @PostMapping("/viewFans")
    public Result ViewFans(@RequestParam int userId){
        //TODO
        return null;
    }
}
