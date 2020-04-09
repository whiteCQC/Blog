package com.blog.service.impl;

import com.blog.mapper.CommentMapper;
import com.blog.model.Comment;
import com.blog.model.CommentKey;
import com.blog.service.CommentService;
import com.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Comment commitComment(Comment comment) {
        Integer max = commentMapper.getMaxCid(comment.getAid());
        int cid;
        if(max!=null){
            cid = max+1;
        }
        else{
            cid = 1;
        }
        comment.setCid(cid);
        comment.setCommentDate(new Date());
        commentMapper.insertComment(comment);
        return comment;
    }

    @Override
    public List<CommentVo> getComments(Integer aid) {
        return commentMapper.getCommentsOfArticle(aid);
    }

    @Override
    public void deleteComment(Comment comment) {
        CommentKey commentKey = new CommentKey();
        commentKey.setAid(comment.getAid());
        commentKey.setCid(comment.getCid());
        commentMapper.deleteComment(commentKey);
    }
}
