package com.blog.mapper;

import com.blog.model.Comment;
import com.blog.model.CommentKey;
import com.blog.vo.CommentVo;

import java.util.List;

public interface CommentMapper {
    int insertComment (Comment comment);

    int deleteComment (CommentKey key);

    Integer getMaxCid(Integer aid);

    List<CommentVo> getCommentsOfArticle(Integer aid);
}
