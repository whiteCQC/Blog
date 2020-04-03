package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.User;
import org.springframework.web.bind.annotation.*;

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
    /**
     *
     * @param uid
     * @return 获得该用户的所有收藏夹（有MarkedVO，包含用户ID，收藏夹ID，收藏夹名）markedList
     */
    @GetMapping("/blog/personal/markedTest")
    public Result viewMarked(@RequestParam(value = "uid")int uid){
        List<Marked> markedList=new ArrayList<>();
        markedList.add(new Marked(uid,0,"默认收藏夹"));
        markedList.add(new Marked(uid,1,"收藏夹1"));
        markedList.add(new Marked(uid,2,"收藏夹2"));
        markedList.add(new Marked(uid,3,"收藏夹3"));
        return Result.success(markedList);
    }

    @PostMapping("/blog/personal/marked/addArticleTest")
    public Result AddMarkedArticle(@RequestBody MarkedArticle marked_article){
        System.out.println(marked_article.getUid());
        return Result.success("收藏成功");
    }


    /**
     *
     * @param uid
     * @param markid
     * @return 获得指定用户的某个收藏夹下的文章信息（不包括文章正文）
     */
    @GetMapping("/blog/personal/marked/article")
    public Result viewMarkedArticle(@RequestParam(value = "uid")int uid,@RequestParam("markid")int markid){
        Marked m1=new Marked(1,0,"默认收藏夹");
        List<Article> list1=new ArrayList<>();
        for(int i=0;i<10;i++)
            list1.add(generateArticle());
        m1.setArticleList(list1);
        return Result.success(m1);
    }

    private Article generateArticle(){
        Article a=new Article();
        int id=(int)(Math.random()*100);
        a.setAid(id);
        a.setArticleTitle("文章"+id);
        a.setArticleContent("内容"+id);
        a.setDate(new Date());
        a.setViewNum(id*100);
        return a;
    }
}
