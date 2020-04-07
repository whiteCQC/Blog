package com.blog.service;

import com.blog.model.SpecialColumn;

import java.util.List;

public interface SpecialColumnService {
    List<SpecialColumn> getSpecialColumnsByUid(Integer uid);
}
