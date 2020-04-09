package com.blog;

import com.blog.bean.Result;
import com.blog.mapper.*;
import com.blog.model.*;
import com.blog.service.*;
import com.blog.vo.CommentVo;
import com.blog.vo.Fan;
import com.blog.vo.MarkedMoveVo;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    SpecialColumnService specialColumnService;

    @BeforeAll
    public static void init() {
        System.out.println("开始测试-----------------");
    }

    @AfterAll
    public static void after() {
        System.out.println("测试结束-----------------");
    }

    @Test
    void test1()
    {
        int test = 1;

        List<Article> l = specialColumnService.getSPArticles(52);
        System.out.println(l);
        for (Article article : l) {
            System.out.println(article);
        }
        Assertions.assertEquals(1,test);

    }





}
