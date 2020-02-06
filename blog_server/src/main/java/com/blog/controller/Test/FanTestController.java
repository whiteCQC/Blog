package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.vo.Fan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FanTestController {
    @PostMapping("/FanAddTest")
    public Result FanFollow(@RequestBody Fan fan){
        System.out.println(fan.getFollowId()+","+fan.getFollowedId());
        return Result.success();
    }
}
