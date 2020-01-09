package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/article")
    public Result createArticle(@RequestBody Article article)
    {
        article.setDate(new Date());
        articleService.createArticle(article);
        return Result.success(article);
    }

    @GetMapping("/article/{keyword}")
    public Result searchArticle(@PathVariable String keyword)
    {
        List<Article> list = articleService.getArticleByKeyword(keyword);
        return Result.success(list);
    }




}
