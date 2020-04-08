package com.blog;

import com.blog.bean.Result;
import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.service.*;
import com.blog.vo.CommentVo;
import com.blog.vo.Fan;
import com.blog.vo.MarkedMoveVo;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    SpecialColumnService specialColumnService;

    @Test
    void test1()
    {
        Marked marked = new Marked();
        marked.setMarkName("插入测试3");
        marked.setUid(1);
        System.out.println("前" + marked.getMarkId());
        marked = markService.addMarked(marked);
        System.out.println("后" + marked.getMarkId());

    }



    @Test
    void markTest()
    {
//        MarkedArticle markedArticle = new MarkedArticle();
//        markedArticle.setUid(1);
//        markedArticle.setMarkId(1);
//        markedArticle.setAid(1);
//        System.out.println((markService.addMarkedArticle(markedArticle)));
//        markedArticle.setAid(2);
//        System.out.println((markService.addMarkedArticle(markedArticle)));
//        MarkedMoveVo markedMoveVo = new MarkedMoveVo(1,2,1,4);
//
//        markService.moveMarkedArticle(markedMoveVo);

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
