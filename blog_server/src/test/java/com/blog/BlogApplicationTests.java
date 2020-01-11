package com.blog;

import com.blog.mapper.ArticleMapper;
import com.blog.mapper.UserMapper;
import com.blog.model.Article;
import com.blog.model.User;
import com.blog.service.ArticleService;
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

    @Test
    void createUser() {
//        User user = new User();
//        user.setUname("aaa");
//        user.setPassword("123456");
//        user.setEmail("dxy@1234.com");
//        user.setBirth(new Date());
//        user.setGender("M");
//        userMapper.insert(user);
//        User u1 = userMapper.selectByEmail("1234@dxy.com");
        List<Article> list = articleMapper.selectByKeyword("测试");
        for (Article a:list) {
            System.out.println(a.getArticleTitle());
        }
          articleMapper.updateViewNum(1);
          articleService.updateViewNum(1);

        System.out.println("--------------");
    }

}
