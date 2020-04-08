package com.blog.service.impl;

import com.blog.mapper.MarkedArticleMapper;
import com.blog.mapper.MarkedMapper;
import com.blog.model.Article;
import com.blog.model.Marked;
import com.blog.model.MarkedArticle;
import com.blog.model.MarkedKey;
import com.blog.service.MarkService;
import com.blog.vo.MarkedMoveVo;
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
    public Marked addMarked(Marked marked) {
        //保证名称不重复和用户内部markId自增
        List<Marked> markedList = markedMapper.selectByUid(marked.getUid());
        if(notContainMarkedName(markedList,marked))
        {
            try {
                markedMapper.insert(marked);
                return markedMapper.getMarked(marked.getUid(),marked.getMarkName());
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                return null;
            }
        }
        else
            return null;

    }

    @Override
    public void moveMarkedArticle(MarkedMoveVo markedMoveVo) {
        markedArticleMapper.moveMarkedArticle(markedMoveVo);
    }

    @Override
    public void deleteMarked(Integer uid, Integer markId) {
        markedArticleMapper.deleteByMarked(new MarkedKey(uid, markId));
        markedMapper.deleteByName(uid, markId);

    }

    @Override
    public List<Article> getMarkedArticles(Integer uid, Integer markId) {
        return markedMapper.getMarkedArticles(uid, markId).getArticleList();
    }

    @Override
    public boolean addMarkedArticle(MarkedArticle markedArticle) {
        int row = markedArticleMapper.insert(markedArticle);
        return row != 0;
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
