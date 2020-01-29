package com.blog.service.impl;

import com.blog.mapper.MarkedArticleMapper;
import com.blog.mapper.MarkedMapper;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.MarkedKey;
import com.blog.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {

    @Autowired
    MarkedMapper markedMapper;

    @Autowired
    MarkedArticleMapper markedArticleMapper;

    @Override
    public List<Marked> getMarkedListByUid(Integer uid) {

        return markedMapper.selectByUid(uid);
    }

    @Override
    public boolean addMarked(Marked marked) {
        //保证名称不重复和用户内部markId自增
        List<Marked> markedList = markedMapper.selectByUid(marked.getUid());
        if(notContainMarkedName(markedList,marked))
        {
            Integer markId = markedMapper.getMaxMarkId(marked.getUid());
            marked.setMarkId(markId+1);
            markedMapper.insert(marked);
            return true;
        }
        else
            return false;

    }

    @Override
    public void deleteMarked(Integer uid, String markName) {
        Integer markId = markedMapper.getMarkId(uid, markName);
        markedArticleMapper.deleteByMarked(new MarkedKey(uid, markId));
        markedMapper.deleteByName(uid, markName);

    }

    @Override
    public List<Article> getMarkedArticles(Integer uid, Integer markId) {
        return markedMapper.getMarkedArticles(uid, markId).getArticleList();
    }

    @Override
    public boolean addMarkedArticle(MarkedArticle markedArticle) {
        int row = markedArticleMapper.insert(markedArticle);
        if(row == 0)
            return false;
        else
            return true;
    }

    @Override
    public void deleteMarkedArticle(MarkedArticle markedArticle) {
        markedArticleMapper.deleteByPrimaryKey(markedArticle);
    }

    private boolean notContainMarkedName(List<Marked> markedList, Marked marked)
    {
        for (Marked m: markedList)
        {
            if(m.getMarkName().trim().equals(marked.getMarkName().trim()))
            return false;
        }
        return true;
    }
}
