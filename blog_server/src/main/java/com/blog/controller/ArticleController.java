package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
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
     * @return 返回除了文章具体内容的文章list信息 list的总页数
     */
    @GetMapping("/article/search")
    public Result searchArticle(@RequestParam(value = "keyword") String keyword
            ,@RequestParam(value = "pageNum",defaultValue = "1")int pageNum)
    {
        PageInfo<Article> pageInfo = articleService.getArticleByKeyword(keyword, pageNum);
        HashMap< String, Object > map = new HashMap<>();
        List<Article> list = pageInfo.getList();
        Long total= pageInfo.getTotal();//结果的总页数
        map.put("total", total);
        map.put("list", list);
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
     * @return 获得该文章全部的详细信息，包括 该文章的所有内容article
     *                                     作者的基本信息（作者id，名称，粉丝数）author
     *                                     总文章数量 articleNum
     *                                     关注数量 fanNum
     *                                     总访问量（作者所有文章点击量总和）totalView
     *                                     最新的前五篇文章名以及aid  newArticles
     */
    @GetMapping("/article/detail")
    public Result ArticleDetail(@RequestParam(value = "aid")int aid){
        Article article = articleService.getArticleById(aid);
        return Result.success(article);
    }

    /**
     *
     * @param article
     * @return 修改文章，（仅作者和点击数不可能变化）
     */
    @PostMapping("/article/modify")
    public Result ArticleModify(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success();
    }
}
