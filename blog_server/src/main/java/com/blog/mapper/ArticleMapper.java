package com.blog.mapper;

import com.blog.model.Article;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(Article record);

    Article selectByPrimaryKey(Integer aid);

    List<Article> selectByUid(Integer uid);

    List<Article> getNewArticle(Integer uid);

    List<Article> getAll();

    int updateViewNum(Integer aid);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectByKeyword(String keyword);

    Map<String, Integer> getArticleInfoByUid(Integer uid);
}