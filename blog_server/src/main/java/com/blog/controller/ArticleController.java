package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.User;
import com.blog.service.ArticleService;
import com.blog.service.UserService;
import com.blog.vo.Author;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

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
        return Result.success();
    }

    /**
     * 搜索文章
     * @param keyword
     * @param pageNum
     * @return 返回除了文章具体内容的文章list信息 list的总页数
     */
    @GetMapping("/article/search")
    public Result searchArticle(@RequestParam(value = "keywords") String keyword
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
     *     总页数total
     * @return
     */
    @GetMapping("/article/hot")
    public Result HotArticle(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum){
        List<Article> list = articleService.getHotArticle();
        return Result.success(list);
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
        Author author = new Author();
        int uid = article.getUid();
        Map<String, Integer> authorArticleInfo = articleService.getArticleInfoByUser(uid);
        User user = userService.getAuthor(aid);

        author.setArticleNum(authorArticleInfo.get("article_num"));
        author.setTotalView(authorArticleInfo.get("total_view"));
        author.setFanNum(userService.getFanNum(uid));
        author.setUid(user.getUid());
        author.setName(user.getUname());
        author.setNewArticles(articleService.getNewArticleByUser(uid));

        HashMap< String, Object > map = new HashMap<>();
        map.put("article", article);
        map.put("author", author);
        return Result.success(map);
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
