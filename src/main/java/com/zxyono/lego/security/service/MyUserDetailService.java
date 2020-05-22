package com.zxyono.lego.security.service;

import com.zxyono.lego.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * 没什么用处，管理员登录也是使用JWT来维护登录状态
 */
@Component
@Slf4j
public class MyUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //通过username查询用户
        //根据自己的业务获取用户信息
        //SelfUserDetails user = userMapper.getUser(username);
        //模拟从数据库获取到用户信息
        Admin admin = new Admin();
        if(admin == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }

        Set authoritiesSet = new HashSet();
        // 模拟从数据库中获取用户权限
        authoritiesSet.add(new SimpleGrantedAuthority("test:list"));
        authoritiesSet.add(new SimpleGrantedAuthority("test:add"));
        admin.setAuthorities(authoritiesSet);

        return admin;
    }
}