package com.blog.service.impl;

import com.blog.mapper.MarkedMapper;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    MarkedMapper markedMapper;

    @Override
    public List<Marked> getMarkedListByUid(Integer uid) {

        return markedMapper.selectByUid(uid);
    }

    @Override
    public void addMarked(Marked marked) {
        markedMapper.insert(marked);
    }

    @Override
    public List<Article> getMarkedArticles(Integer uid, Integer markId) {
        return markedMapper.getMarkedArticles(uid, markId).getArticleList();
    }
}
