package com.blog.mapper;

import com.blog.model.Comment;
import com.blog.model.CommentKey;

public interface CommentMapper {
    int insertComment (Comment comment);
    int deleteComment (CommentKey key);
}
