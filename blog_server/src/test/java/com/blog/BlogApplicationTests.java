package com.blog;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.Article;
import com.blog.model.User;
import com.blog.service.ArticleService;
import com.blog.service.MarkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    MarkService markService;

    @Test
    void articleTest() {
//        User user = new User();
//        user.setUname("aaa");
//        user.setPassword("123456");
//        user.setEmail("dxy@1234.com");
//        user.setBirth(new Date());
//        user.setGender("M");
//        userMapper.insert(user);
//        User u1 = userMapper.selectByEmail("1234@dxy.com");
        List<Article> list = markService.getMarkedArticles(1,1);
        for (Article a:list) {
            System.out.println(a.getArticleTitle());
        }


        System.out.println("--------------");
    }

}
