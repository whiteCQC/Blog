package com.blog.service.impl;

import com.blog.mapper.SpecialColumnMapper;
import com.blog.model.SpecialColumn;
import com.blog.service.SpecialColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class SpecialColumnServiceImpl implements SpecialColumnService {

    @Autowired
    SpecialColumnMapper specialColumnMapper;
    @Override
    public List<SpecialColumn> getSpecialColumnsByUid(Integer uid) {
        return specialColumnMapper.getSpecialColumnsByUid(uid);
    }
}
