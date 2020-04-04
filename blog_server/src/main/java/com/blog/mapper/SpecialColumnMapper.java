package com.blog.mapper;

import com.blog.model.SpecialColumn;

import java.util.List;

public interface SpecialColumnMapper {
    List<SpecialColumn> getSpecialColumnsByUid(Integer uid);//得到用户的专栏

    SpecialColumn getSPWithArticle(Integer spColId);//得到一个专栏内文章

    int addSP(SpecialColumn sp);
}
