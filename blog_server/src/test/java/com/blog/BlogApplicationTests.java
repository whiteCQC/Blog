package com.blog;

import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.service.CommentService;
import com.blog.service.MarkService;
import com.blog.vo.Fan;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    MarkedMapper markedMapper;
    @Autowired
    MarkedArticleMapper markedArticleMapper;
    @Autowired
    CommentService commentService;

    @Autowired
    MarkService markService;

    @Test
    void test1()
    {
        Marked marked= new Marked();
        marked.setUid(1);
        marked.setMarkName("收藏夹3");
        boolean flag = markService.addMarked(marked);
        System.out.println(flag);

    }


    @Test
    void articleTest() {
        

        Article article = articleMapper.selectByPrimaryKey(1);

        List<Article>  l1 = articleMapper.selectByUid(2);

        List<Article> l2 = articleMapper.getNewArticle(2);
        for (Article a: l2) {
            System.out.println(a.getUid()+a.getArticleTitle());

        }

        List<Article> l3 = articleMapper.getAll();


        article.setViewNum(200);
        article.setAid(1);
        article.setDate(new Date());
        article.setArticleTitle("百度新闻——海量中文资讯平台");
        article.setArticleContent("百度新闻是包含海量资讯的新闻服务平台,真实反映每时每刻的新闻热点。" +
                "您可以搜索新闻事件、热点话题、人物动态、产品资讯等,快速了解它们的最新进展。");
        article.setUid(1);

        articleMapper.updateByPrimaryKeyWithBLOBs(article);

        Map<String, Integer> map =  articleMapper.getArticleInfoByUid(1);

        System.out.println(map);

    }

    @Test
    void userTest() {
        User user = new User();
        user.setUname("今天");
        user.setPassword("ddadff");
        user.setBirth(new Date());
        user.setEmail("ddddd");
        user.setGender("g");


    }

    @Test
    void markTest()
    {

        markedMapper.deleteByName(2, "测试");

        Marked marked = new Marked();
        marked.setMarkId(8);
        marked.setMarkName("我的");
        marked.setUid(2);
        markedMapper.insert(marked);
        marked.setMarkId(3);
        markedMapper.updateByPrimaryKey(marked);

        List <Marked> l1 = markedMapper.selectByUid(2);
        for ( Marked m : l1)
        {
            System.out.println(m.getMarkName());
        }
        Marked m = markedMapper.getMarkedArticles(1, 1);
        for (Article a:m.getArticleList())
        {
            System.out.println(a.getArticleTitle());
        }

        int id = markedMapper.getMaxMarkId(2);
        System.out.println(id);

        id = markedMapper.getMarkId(1, "默认收藏夹");
        System.out.println(id);

    }

    @Test
    void markedArticleTest()
    {
        MarkedArticle markedArticle = new MarkedArticle();
        markedArticle.setAid(1);
        markedArticle.setMarkId(3);
        markedArticle.setUid(2);
        markedArticleMapper.deleteByPrimaryKey(markedArticle);

        int i = markedArticleMapper.insert(markedArticle);
        System.out.println(i);
        i = markedArticleMapper.insert(markedArticle);
        System.out.println(i);
        MarkedKey key = new MarkedKey(2,3);
        markedArticleMapper.deleteByMarked(key);


    }

    @Autowired
    FollowerMapper followerMapper;

    @Test
    void followTest()
    {
        Follower f = new Follower();
        f.setFollowerId(3);
        f.setUid(1);
        int i = followerMapper.insert(f);
        System.out.println(i);
        i = followerMapper.insert(f);
        System.out.println(i);
        followerMapper.deleteByPrimaryKey(f);
        i = followerMapper.getFanNum(1);
        System.out.println(i);
        List<Fan> fans2 = followerMapper.getFansOfUser(1);
        for(Fan fan:fans2)
        {
            System.out.println(fan.getAuthorName());
            System.out.println(fan.getFollowerName());
        }
        List<Fan> fans1 = followerMapper.getConcernsOfUser(1);
        for(Fan fan:fans1)
        {
            System.out.println(fan.getAuthorName());
            System.out.println(fan.getFollowerName());
        }

    }

}
