package com.zxyono.lego.service.impl;

import com.zxyono.lego.entity.Role;
import com.zxyono.lego.mapper.RoleMapper;
import com.zxyono.lego.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> queryRoleListByAdminName(String name) {
        return roleMapper.findRoleByAdminName(name);
    }

    @Override
    public Integer createRoleWithParams(Long adminId, String rolename) {
        Role role = new Role();
        role.setAdminId(adminId);
        role.setRoleName(rolename);
        return roleMapper.insert(role);
    }
}