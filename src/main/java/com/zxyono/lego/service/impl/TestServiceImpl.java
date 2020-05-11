package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxyono.lego.entity.TestObject;
import com.zxyono.lego.mapper.TestMapper;
import com.zxyono.lego.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TestServiceImpl implements TestService {
    @Autowired
    public TestMapper testMapper;

    public List<TestObject> getByName(String name) {
        List<TestObject> result = testMapper.selectList(new QueryWrapper<TestObject>().like("name",name));
        return result;
    }
}