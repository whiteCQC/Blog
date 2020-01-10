package com.blog.controller;

import com.blog.bean.Result;
import com.blog.model.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    /**
     *
     * @return 获得当前的热门文章(不包括具体的内容)
     */
    @GetMapping("/hotArticle")
    public Result getHotArticles(){
        //TODO

        return null;
    }

    /**
     *
     * @return 详细的文章内容
     */
    @PostMapping("/articleDetail")
    public Result getArticleDetail(){
        //TODO
        return null;
    }

    /**
     *
     * @param article
     * @return 发布文章
     */
    @PostMapping("/releaseArticle")
    public Result ReleaseArticle(@RequestBody Article article){
        //TODO

        return null;
    }

    /**
     *
     * @param article
     * @return 文章修改
     */
    @PostMapping("/modifyArticle")
    public Result ModifyArticle(@RequestBody Article article){
        //TODO

        return null;
    }
}
