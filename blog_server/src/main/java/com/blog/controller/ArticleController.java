package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 发表文章
     * @param article{包含用户ID，标题，type，mode，专栏，正文}
     * @return
     */
    @PostMapping("/article/create")
    public Result createArticle(@RequestBody Article article)
    {

        article.setDate(new Date());
        articleService.createArticle(article);
        return Result.success(article);
    }

    /**
     * 搜索文章
     * @param keyword
     * @param pageNum
     * @return 返回除了文章具体内容的文章list信息
     */
    @GetMapping("/article/search")
    public Result searchArticle(@RequestParam(value = "keyword") String keyword
            ,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum)
    {
        List<Article> list = articleService.getArticleByKeyword(keyword, pageNum);
        HashMap< String, Object > map = new HashMap<>();
        map.put("list",list);
        map.put("total",5);
        return Result.success(map);
    }

    /**
     * 返回热门文章list，包含除文章具体内容的信息
     * @return
     */
    @GetMapping("/article/hot")
    public Result HotArticle(){
        //TODO
        return null;
    }

    /**
     *
     * @return 获得该文章全部的详细信息
     */
    @GetMapping("/article/detail")
    public Result ArticleDetail(@RequestParam(value = "aid")int aid){
        //TODO

        return null;
    }

    /**
     *
     * @param article
     * @return 修改文章，（仅作者和点击数不可能变化）
     */
    @PostMapping("/article/modify")
    public Result ArticleModify(@RequestBody Article article){
        //TODO
        return null;
    }
}
