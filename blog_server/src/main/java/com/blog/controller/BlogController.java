package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.User;
import com.blog.service.ArticleService;
import com.blog.service.MarkService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    MarkService markService;


    /**
     *
     * @param uid
     * @return 查看个人主页，包括个人信息userInfo，个人文章信息articleInfo，个人收藏夹信息 markInfo
     */
    @GetMapping("/blog/personal")
    public Result viewPersonalBlog(@RequestParam(value = "uid")int uid){
        User userInfo = userService.getUserById(uid);
        List<Article> articleInfo = articleService.getArticleByUser(uid, 1);
        List<Marked> markInfo = markService.getMarkedListByUid(uid);
        HashMap< String, Object > map = new HashMap<>();
        map.put("userInfo", userInfo);
        map.put("articleInfo", articleInfo);
        map.put("markInfo", markInfo);
        return Result.success(map);
    }

    /**
     *
     * @param uid
     * @return 获得指定用户的收藏夹信息（该用户的收藏夹的列表）
     */
    @GetMapping("/blog/personal/marked")
    public Result viewMarked(@RequestParam(value = "uid")int uid){
        List<Marked> markList = markService.getMarkedListByUid(uid);
        return Result.success(markList);
    }

    /**
     *
     * @param uid
     * @param markid
     * @return 获得指定用户的收藏夹下的文章信息（不包括文章正文）
     */
    @GetMapping("/blog/personal/marked/article")
    public Result viewMarkedArticle(@RequestParam(value = "uid")int uid,@RequestParam("markid")int markid){
        //TODO
        return null;
    }

    /**
     *
     * @param marked
     * @return 添加新的收藏夹(包括uid和收藏夹名称，名称不可以和现有的重复)
     */
    @PostMapping("/blog/personal/marked/addMarked")
    public Result AddMarked(@RequestBody Marked marked){
        //TODO
        return null;
    }

    /**
     *
     * @param marked
     * @return 删除收藏夹(包括uid和收藏夹名称，将会连带该收藏夹下的收藏记录一起删除)
     */
    @PostMapping("/blog/personal/marked/deleteMarked")
    public Result DeleteMarked(@RequestBody Marked marked){
        //TODO
        return null;
    }

    /**
     *
     * @param
     * @return 添加文章到收藏夹(包括uid和收藏夹id和文章id，不可以将文章重复添加到同一个收藏夹当中)
     */
    @PostMapping("/blog/personal/marked/addArticle")
    public Result AddMarkedArticle(@RequestBody MarkedArticle marked_article){
        //TODO
        return null;
    }

    /**
     *
     * @param
     * @return 取消收藏夹中的文章收藏(包括uid和收藏夹id和文章id)
     */
    @PostMapping("/blog/personal/marked/deleteArticle")
    public Result DeleteMarkedArticle(@RequestBody MarkedArticle marked_article){
        //TODO
        return null;
    }
}
