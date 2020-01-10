package com.blog.controller;

import com.blog.bean.Result;
import com.blog.vo.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @PostMapping("/commentSubmit")
    public Result CommentSubmit(@RequestBody Comment comment){
        //TODO

        return null;
    }

    @PostMapping("/commentReply")
    public Result CommentReply(@RequestBody Comment comment){
        //TODO
        return null;
    }

    @PostMapping("/commentDelete")
    public Result CommentDelete(@RequestBody Comment comment){
        //TODO

        return null;
    }
}
