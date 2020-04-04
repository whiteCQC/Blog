package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.User;
import com.blog.service.ArticleService;
import com.blog.service.MarkService;
import com.blog.service.UserService;
import com.blog.vo.MarkedMoveVo;
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
     * @return 获得指定用户的某个收藏夹下的文章信息（不包括文章正文）
     */
    @GetMapping("/blog/personal/marked/article")
    public Result viewMarkedArticle(@RequestParam(value = "uid")int uid,@RequestParam("markid")int markid){
        return Result.success(markService.getMarkedArticles(uid, markid));
    }

    /**
     *
     * @param marked
     * @return 添加新的收藏夹(包括uid和收藏夹名称，名称不可以和现有的重复),成功返回新的marked
     */
    @PostMapping("/blog/personal/marked/addMarked")
    public Result addMarked(@RequestBody Marked marked){

        Marked newMarked = markService.addMarked(marked);
        if(newMarked != null)
        {
            return Result.success(newMarked);
        }
        else
        {
            return Result.error("收藏夹名字重复");
        }
    }

    /**
     *
     * @param marked
     * @return 删除收藏夹(包括uid和收藏夹id，将会连带该收藏夹下的收藏记录一起删除)
     */
    @PostMapping("/blog/personal/marked/deleteMarked")
    public Result deleteMarked(@RequestBody Marked marked){
        markService.deleteMarked(marked.getUid(),marked.getMarkId());
        return Result.success();
    }

    /**
     *
     * @param
     * @return 添加文章到收藏夹(包括uid和收藏夹id和文章id，不可以将文章重复添加到同一个收藏夹当中)
     */
    @PostMapping("/blog/personal/marked/addArticle")
    public Result addMarkedArticle(@RequestBody MarkedArticle marked_article){
        if(markService.addMarkedArticle(marked_article))
            return Result.success("收藏成功");
        else
            return Result.error("该文章已被收藏");
    }

    /**
     *
     * @param
     * @return 取消收藏夹中的文章收藏(包括uid和收藏夹id和文章id)
     */
    @PostMapping("/blog/personal/marked/deleteArticle")
    public Result deleteMarkedArticle(@RequestBody MarkedArticle marked_article){
        markService.deleteMarkedArticle(marked_article);
        return Result.success("删除成功");
    }


    /**
     *
     * @return 移动收藏的文章
     */
    @PostMapping("/blog/personal/marked/moveArticle")
    public Result moveMarkedArticle(@RequestBody MarkedMoveVo markedMoveVo){
        markService.moveMarkedArticle(markedMoveVo);
        return Result.success("移动成功");
    }
}
