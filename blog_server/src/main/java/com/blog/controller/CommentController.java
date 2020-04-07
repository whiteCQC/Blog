package com.blog.controller;

import com.blog.bean.Result;
import com.blog.service.CommentService;
import com.blog.model.Comment;
import com.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     *
     * @param comment(评论内容，文章id，评论者id),成功需要返回该条Comment信息
     * @return 评论提交
     */
    @PostMapping("/commentSubmit")
    public Result commentSubmit(@RequestBody Comment comment){
        return Result.success(commentService.commitComment(comment));
    }

    /**
     *
     * @param comment(评论内容，文章id，评论者id，被回复者id)  第4个应该是commentOfCid，是被回复的评论的cid
     * @return 评论回复
     */
    @PostMapping("/commentReply")
    public Result commentReply(@RequestBody Comment comment){
        commentService.commitComment(comment);
        return Result.success();
    }

    /**
     *
     * @param comment（待定）  删除需要主键aid 和 cid
     * @return 评论删除
     */
    @PostMapping("/commentDelete")
    public Result commentDelete(@RequestBody Comment comment){
        commentService.deleteComment(comment);
        return Result.success("删除成功");
    }

    /**
     *
     * @param aid
     * @return 获取文章的评论(用CommentVo,因为需要用户的昵称)
     */
    @GetMapping("/getComments")
    public Result getComments(@RequestParam(value = "aid")int aid){
        List<CommentVo> comments = commentService.getComments(aid);
        return Result.success(comments);
    }
}
