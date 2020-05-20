package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.mapper.AdminMapper;
import com.zxyono.lego.mapper.RoleMapper;
import com.zxyono.lego.repository.RedisRepository;
import com.zxyono.lego.service.AdminService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RedisRepository redisRepository;

    @Override
    public Admin queryAdminByName(String name) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_name", name));
    }
}