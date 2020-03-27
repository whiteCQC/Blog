package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.model.Comment;
import com.blog.model.CommentKey;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void commitComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    @Override
    public void deleteComment(CommentKey commentKey) {

    }
}
