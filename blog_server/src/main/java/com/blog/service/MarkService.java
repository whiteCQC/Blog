package com.blog.service;

import com.blog.model.Marked;

import java.util.List;

public interface MarkService {
    List<Marked> getMarkedListByUid(Integer uid);

    void  addMarked(Marked marked);
}
