package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/article/hotTest")
    public Result HotArticle(){
        Article a1=new Article();
        a1.setAid(1);
        a1.setArticleContent("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。");
        a1.setArticleTitle("百度新闻——海量中文资讯平台");
        a1.setDate(new Date());
        a1.setViewNum(1);

        Article a2=new Article();
        a2.setAid(2);
        a2.setArticleContent("蜂鸟网,中国专业摄影门户网站,学习摄影技巧、分享摄影图片,这里有极具个性的摄影作品,全新数码相机、镜头等摄影器材的行情评测;涉及生活摄影、旅游摄影、风光摄影等");
        a2.setArticleTitle("蜂鸟网 - 中国专业影像门户,摄影爱好者分享摄影技巧和作品的网站");
        a2.setDate(new Date());
        a2.setViewNum(2);
        List<Article> list=new ArrayList<>();
        list.add(a1);
        list.add(a2);
        //Map<String,Object> map =new HashMap<>();
        //map.put("hots",list);
        return Result.success(list);
    }

    @PostMapping("/loginTest")
    public Result userLogin(@RequestBody User userInfo)
    {
        System.out.println(userInfo.getEmail());
        System.out.println(userInfo.getPassword());

        HashMap < String, Object > map = new HashMap<>();
        map.put("uid", "10");
        map.put("uname","十");
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
}
