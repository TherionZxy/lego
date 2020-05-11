package com.zxyono.lego.service;

import com.zxyono.lego.entity.TestObject;

import java.util.List;

public interface TestService {
    public List<TestObject> getByName(String name);
}
