package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.User;
import com.blog.vo.Fan;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class FanTestController {
    @PostMapping("/FanAddTest")
    public Result FanFollow(@RequestBody Fan fan){
        System.out.println(fan.getFollowId()+","+fan.getFollowedId());
        return Result.success();
    }

    /**
     *
     * @return 对应用户的所拥有的粉丝基本信息{每个粉丝的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewFansTest")
    public Result ViewFans(@RequestParam(value = "uid") int userId){
        HashMap< String, Object > map = new HashMap<>();

        List<User> list=new ArrayList<>();

        for(int i=0;i<67;i++){
            User u=new User();
            u.setUid(i);
            u.setUname("JOJO"+i);
            list.add(u);
        }

        map.put("fans",list);
        return Result.success(map);
    }

    /**
     *
     * @return 对应用户的所关注的用户信息{每个用户的ID，用户名}，list对应key：fans
     */
    @GetMapping("/viewConcernsTest")
    public Result ViewConcerns(@RequestParam(value = "uid") int userId){
        HashMap < String, Object > map = new HashMap<>();

        List<User> list=new ArrayList<>();

        for(int i=0;i<67;i++){
            User u=new User();
            u.setUid(i);
            u.setUname("DIO"+i);
            list.add(u);
        }

        map.put("fans",list);
        return Result.success(map);
    }
}
