package com.zxyono.lego.security.filter;

import com.zxyono.lego.entity.Role;
import com.zxyono.lego.security.token.WechatAuthenticationToken;
import com.zxyono.lego.util.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * JWT拦截器
     * @param httpServletRequest
     * @param httpServletResponse
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("token");
        String openid = null;
        if(token != null) {
            try {
                openid = jwtTokenUtils.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {

            } catch (ExpiredJwtException e) {

            } catch (SignatureException e) {

            }
        } else {

        }

        if (openid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            // 权限为空，所以开始获取用户权限
//            // 1. 查询用户的权限列表
//            List<Role> roles;
//            // 2. 将权限列表中的权限转为SimpleGrantedAuthority（使用java流机制）
//            List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
//            // 3. 将权限列表设置到上下文中
//            SecurityContextHolder.getContext().setAuthentication(new WechatAuthenticationToken(openid, authorities));
            // 所有微信用户权限都是"USER"
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            SecurityContextHolder.getContext().setAuthentication(new WechatAuthenticationToken(openid, authorities));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}