package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class BlogTestController {
    @GetMapping("/blog/personalTest")
    public Result viewPersonalBlog(@RequestParam(value = "uid")int uid){
        HashMap< String, Object > map = new HashMap<>();
        User u=new User();
        u.setUname("萝卜青菜");
        u.setEmail("666666@163.com");
        map.put("userInfo", u);

        map.put("markInfo", null);

        List<Article> articleInfo=new ArrayList<>();
        Article a1=new Article();
        a1.setViewNum(100);
        a1.setDate(new Date());
        a1.setArticleContent("内容1");
        a1.setArticleTitle("标题1");
        a1.setMode(0);
        a1.setAid(1);

        Article a2=new Article();
        a2.setViewNum(200);
        a2.setDate(new Date());
        a2.setArticleContent("内容2");
        a2.setArticleTitle("标题2");
        a2.setMode(1);
        a2.setAid(2);

        articleInfo.add(a1);
        articleInfo.add(a2);
        map.put("articleInfo", articleInfo);

        return Result.success(map);
    }

}
