package com.blog;

import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import com.blog.service.MarkService;
import com.blog.service.UserService;
import com.blog.vo.Fan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    MarkedMapper markedMapper;
    @Autowired
    MarkedArticleMapper markedArticleMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @Autowired
    MarkService markService;

    @Test
    void test1()
    {


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
