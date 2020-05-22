package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.Role;
import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.LoginException;
import com.zxyono.lego.mapper.AdminMapper;
import com.zxyono.lego.mapper.RoleMapper;
import com.zxyono.lego.service.AdminService;
import com.zxyono.lego.util.JwtTokenUtil;
import com.zxyono.lego.util.RedisUtil;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RedisUtil redisUtil;

    @Value("${lego.token.expirationMilliSeconds}")
    private long expirationMilliSeconds;


    @Override
    public ResultMap adminLogin(String username, String password) {
        // 首先查询是否存在用户
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_name", username));

        // 若用户不存在，抛出异常
        if (admin == null) {
            throw new LoginException(ExceptionEnum.USER_NOT_FOUND);
        }

        // 比对密码
        if (!admin.getAdminPwd().equals(password)) {
            throw new LoginException(ExceptionEnum.PASSWORD_WRONG);
        }

        // 否则查询数据库中的权限
        List<Role> roles = roleMapper.findRoleByAdminName(admin.getAdminName());
        Set authoritiesSet = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        admin.setAuthorities(authoritiesSet);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("adminId",admin.getAdminId().toString());
        hashMap.put("authorities",authoritiesSet);

        String token = JwtTokenUtil.generateToken(admin, expirationMilliSeconds);
        redisUtil.hset(token, hashMap);

        hashMap.put("token", token);
        return ResultMap.success("管理员登录成功", hashMap);
    }

    @Override
    public ResultMap adminInfo(Long adminId) {
        Admin admin = adminMapper.selectById(adminId);
        admin.setAdminPwd("");
        return ResultMap.success(admin);
    }
}