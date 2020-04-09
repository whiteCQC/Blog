package com.blog;

import com.blog.bean.Result;
import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.service.*;
import com.blog.vo.CommentVo;
import com.blog.vo.Fan;
import com.blog.vo.MarkedMoveVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Assertions;
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
        int test = 1;
        Assertions.assertEquals(test,1);
        Comment c = new Comment();
        c.setCommentContent("测试");
        c.setUid(2);
        c.setAid(10);
        System.out.println(commentService.commitComment(c));
    }





}
