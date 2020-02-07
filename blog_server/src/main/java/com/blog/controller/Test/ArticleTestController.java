package com.blog.controller.Test;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ArticleTestController {

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

    @GetMapping("/article/detailTest")
    public Result ArticleDetailTest(@RequestParam(value="aid")int aid){
        //System.out.println(aid);
        HashMap< String, Object > map = new HashMap<>();
        Article article=new Article();
        article.setViewNum(200);
        article.setAid(1);
        article.setDate(new Date());
        article.setArticleTitle("百度新闻——海量中文资讯平台");
        article.setArticleContent("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。" +
                "您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。");
        article.setUid(1);
        map.put("article",article);

        User u = new User();
        u.setUid(12);
        u.setUname("一二三");

        map.put("fanNum",2341);
        map.put("author",u);

        map.put("articleNum",10);

        map.put("totalView",30000);

        List<Article> newArticles=new ArrayList<>();
        for(int i=0;i<5;i++){
            Article a=new Article();
            a.setUid(12);
            a.setAid(i+100);
            a.setArticleTitle("当前最新的文章"+i);
        }
        map.put("newArticles",newArticles);


        return Result.success(map);
    }

    @GetMapping("/article/searchTest1")
    public Result searchArticle1(@RequestParam(value = "keyword") String keyword
            ,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum)
    {
        HashMap< String, Object > map = new HashMap<>();
        List<Article> list=new ArrayList<>();
        for(int i=1;i<=1;i++){
            Article a=new Article();
            a.setAid(i);
            a.setUid(i+100);
            a.setArticleTitle("标题"+i);
            a.setArticleContent("文章的内容"+i);
            a.setDate(new Date());
            a.setViewNum(i*1000);
        }

        map.put("total", 1);
        map.put("list", list);
        return Result.success(map);
    }

    @GetMapping("/article/searchTest2")
    public Result searchArticle2(@RequestParam(value = "keyword") String keyword
            ,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum)
    {
        HashMap< String, Object > map = new HashMap<>();
        List<Article> list=new ArrayList<>();
        for(int i=1;i<=11;i++){
            Article a=new Article();
            a.setAid(i);
            a.setUid(i+100);
            a.setArticleTitle("标题"+i);
            a.setArticleContent("文章的内容"+i);
            a.setDate(new Date());
            a.setViewNum(i*1000);
        }

        map.put("total", 11);
        map.put("list", list);
        return Result.success(map);
    }

    @GetMapping("/article/searchTest3")
    public Result searchArticle3(@RequestParam(value = "keywords") String keywords
            ,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum)
    {
        System.out.println(keywords);
        HashMap< String, Object > map = new HashMap<>();
        List<Article> list=new ArrayList<>();
        int start = (pageNum-1)*5+1;
        for(int i=start;i<start+5;i++){
            Article a=new Article();
            a.setAid(i);
            a.setUid(i+100);
            a.setArticleTitle("标题"+i);
            a.setArticleContent("文章的内容"+i);
            a.setDate(new Date());
            a.setViewNum(i*1000);
            list.add(a);
        }

        map.put("total", 76);
        map.put("list", list);
        return Result.success(map);
    }
}
