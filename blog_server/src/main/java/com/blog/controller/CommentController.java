package com.blog.controller;

import com.blog.bean.Result;
import com.blog.service.CommentService;
import com.blog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     *
     * @param comment(评论内容，文章id，评论者id)
     * @return 评论提交
     */
    @PostMapping("/commentSubmit")
    public Result CommentSubmit(@RequestBody Comment comment){
        commentService.commitComment(comment);
        return Result.success();
    }

    /**
     *
     * @param comment(评论内容，文章id，评论者id，被回复者id)  第4个应该是commentOfCid，是被回复的评论的cid
     * @return 评论回复
     */
    @PostMapping("/commentReply")
    public Result CommentReply(@RequestBody Comment comment){
        commentService.commitComment(comment);
        return Result.success();
    }

    /**
     *
     * @param comment（待定）  删除需要主键aid 和 cid
     * @return 评论删除
     */
    @PostMapping("/commentDelete")
    public Result CommentDelete(@RequestBody Comment comment){
        //TODO

        return null;
    }

    /**
     *
     * @param aid
     * @return 获取文章的评论(用CommentVo,因为需要用户的昵称)
     */
    @GetMapping("/getComments")
    public Result GetComments(@RequestParam(value = "aid")int aid){
        //TODO

        return null;
    }
}
