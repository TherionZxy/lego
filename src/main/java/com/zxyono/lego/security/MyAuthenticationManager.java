package com.zxyono.lego.security;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.Role;
import com.zxyono.lego.entity.User;
import com.zxyono.lego.security.token.AdminAuthenticationToken;
import com.zxyono.lego.security.token.WechatAuthenticationToken;
import com.zxyono.lego.service.AdminService;
import com.zxyono.lego.service.RoleService;
import com.zxyono.lego.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限鉴定主要逻辑
 */
@Component
@Slf4j
public class MyAuthenticationManager implements AuthenticationManager {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    public Authentication wechatAuthenticate(Authentication authentication) {
        WechatAuthenticationToken wechatAuthenticationToken = (WechatAuthenticationToken) authentication;
        User user = userService.queryUserByOpenId(wechatAuthenticationToken.getOpenid());

        // 如果用户未在数据库中存在
        if (user == null) {
            // 获取用户信息
            user = new User();
            user.setOpenId(wechatAuthenticationToken.getOpenid());
            userService.createUser(user);

            // 获取权限
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            return new WechatAuthenticationToken(user.getOpenId(), authorities);
        }

        // 如果用户在数据库中存在, 直接获取权限
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new WechatAuthenticationToken(wechatAuthenticationToken.getOpenid(), authorities);
    }

    public Authentication adminAuthenticate(Authentication authentication) {
        AdminAuthenticationToken adminAuthenticationToken = (AdminAuthenticationToken) authentication;
        Admin admin = adminService.queryAdminByName(adminAuthenticationToken.getUsername());

        // 如果管理员未在数据库中存在
        if (admin == null) {
            throw new RuntimeException("用户不存在");
        }

        // 如果管理员在数据库中存在, 比较密码是否相同
        if (!admin.getAdminPwd().equals(adminAuthenticationToken.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 获取权限
        List<Role> roles = roleService.queryRoleListByAdminName(admin.getAdminName());
        List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return new AdminAuthenticationToken(adminAuthenticationToken.getUsername(), authorities);

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 若令牌属于小程序令牌，则按小程序令牌操作继续
        if (authentication instanceof WechatAuthenticationToken) {
            return wechatAuthenticate(authentication);
        }

        // 若令牌属于管理系统令牌，则按管理系统令牌操作继续
        if (authentication instanceof AdminAuthenticationToken) {
            return adminAuthenticate(authentication);
        }

        // 如果既不属于小程序令牌也不属于管理系统令牌，返回null
        return null;
    }
}