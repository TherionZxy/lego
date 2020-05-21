package com.zxyono.lego.security.filter;

import com.zxyono.lego.entity.Role;
import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.AuthenticationException;
import com.zxyono.lego.exception.JwtException;
import com.zxyono.lego.exception.ParamException;
import com.zxyono.lego.security.token.AdminAuthenticationToken;
import com.zxyono.lego.security.token.WechatAuthenticationToken;
import com.zxyono.lego.service.RoleService;
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
import java.util.stream.Collectors;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private RoleService roleService;

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
        String wecaht_token = httpServletRequest.getHeader("wechat-token");
        String admin_token = httpServletRequest.getHeader("admin-token");

        try {
            // 首先判断 请求头中是否有wechat-token
            if (wecaht_token != null) {
                String openid = jwtTokenUtils.getUsernameFromToken(wecaht_token);
                if (openid != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority("USER"));
                    SecurityContextHolder.getContext().setAuthentication(new WechatAuthenticationToken(openid, authorities));
                }
            } else if (admin_token != null) {
                String username = jwtTokenUtils.getUsernameFromToken(admin_token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    List<Role> roles = roleService.queryRoleListByAdminName(username);
                    System.out.println(roles);
                    List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
                    System.out.println(authorities);
                    SecurityContextHolder.getContext().setAuthentication(new AdminAuthenticationToken(username, authorities));
                }
            } else {
//                throw new AuthenticationException(ExceptionEnum.ACCESS_DENIED);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ParamException(ExceptionEnum.PARAM_EXCEPTION);
        } catch (ExpiredJwtException e) {
            throw new JwtException(ExceptionEnum.JWT_EXCEPTION);
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}