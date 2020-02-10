package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class BlogTestController {
    @GetMapping("/blog/personalTest")
    public Result viewPersonalBlog(@RequestParam(value = "uid")int uid){
        HashMap< String, Object > map = new HashMap<>();
        User u=new User();
        u.setUname("订书钉");
        u.setEmail("123456798@126.com");
        map.put("userInfo", u);
        map.put("articleInfo", null);
        map.put("markInfo", null);
        return Result.success(map);
    }
}
