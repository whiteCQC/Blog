package com.blog.controller;

import com.blog.bean.Result;
import com.blog.vo.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    /**
     *
     * @param comment(评论内容，文章id，评论者id)
     * @return 评论提交
     */
    @PostMapping("/commentSubmit")
    public Result CommentSubmit(@RequestBody Comment comment){
        //TODO

        return null;
    }

    /**
     *
     * @param comment(评论内容，文章id，评论者id，被回复者id)
     * @return 评论回复
     */
    @PostMapping("/commentReply")
    public Result CommentReply(@RequestBody Comment comment){
        //TODO
        return null;
    }

    /**
     *
     * @param comment（待定）
     * @return 评论删除
     */
    @PostMapping("/commentDelete")
    public Result CommentDelete(@RequestBody Comment comment){
        //TODO

        return null;
    }
}
