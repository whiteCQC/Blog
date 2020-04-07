package com.blog.service;

import com.blog.model.Comment;
import com.blog.vo.CommentVo;

import java.util.List;

public interface CommentService {

    Comment commitComment(Comment comment);

    void deleteComment(Comment comment);

    List<CommentVo> getComments(Integer aid);

}
