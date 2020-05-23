package com.zxyono.lego.service;

import com.zxyono.lego.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> queryRoleListByAdminName(String name);

    public Integer createRoleWithParams(Long adminId, String role);
}
