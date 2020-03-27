package com.blog.service;

import com.blog.model.Comment;
import com.blog.model.CommentKey;

public interface CommentService {

    void commitComment(Comment comment);

    void deleteComment(CommentKey commentKey);




}
