package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.User;
import com.blog.service.ArticleService;
import com.blog.service.UserService;
import com.blog.vo.Author;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
     *     总页数 total
     * @return
     */
    @GetMapping("/article/hot")
    public Result hotArticle(@RequestParam(value = "pageNum",defaultValue = "1")int pageNum){
        PageInfo<Article> pageInfo = articleService.getHotArticle(pageNum);
        List<Article> list = pageInfo.getList();
        Long total= pageInfo.getTotal();//结果的总页数
        HashMap< String, Object > map = new HashMap<>();
        map.put("total", total);
        map.put("list", list);
        return Result.success(map);

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
    public Result articleDetail(@RequestParam(value = "aid")int aid){
        Article article = articleService.getArticleById(aid);
        if(article == null)
        {
            return Result.error("文章不存在");
        }
        articleService.updateViewNum(aid);
        Author author = new Author();
        int uid = article.getUid();
        Map<String, Number> authorArticleInfo = articleService.getArticleInfoByUser(uid);
        User user = userService.getAuthor(aid);
        System.out.println(authorArticleInfo);
        author.setArticleNum(authorArticleInfo.get("article_num").intValue());
        author.setTotalView(authorArticleInfo.get("total_view").intValue());
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
    public Result articleModify(@RequestBody Article article){
        articleService.updateArticle(article);
        return Result.success();
    }

    /**
     *
     * @param article ,文章id
     * @return 删除文章,返回新的文章list（articleInfo）  需要用户ID才能返回新的文章LIST
     */
    @PostMapping("/article/del")
    public Result articleDel(@RequestBody Article article){
        articleService.deleteArticle(article.getAid());
        return Result.success("删除成功");
    }

    /**
     *
     * @param aid 文章id
     * @return 仅获取对应article的信息（即不包含浏览人数等其他信息）
     */
    @GetMapping("/article/simple")
    public Result getSimpleArticle(@RequestParam(value = "aid")int aid){
        Article article = articleService.getArticleById(aid);
        HashMap< String, Object > map = new HashMap<>();
        map.put("article", article);
        return Result.success(article);
    }

}
