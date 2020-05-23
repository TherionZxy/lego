package com.zxyono.lego.security.filter;

import com.alibaba.fastjson.JSON;
import com.zxyono.lego.constant.HttpStatus;
import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.User;
import com.zxyono.lego.util.JwtTokenUtil;
import com.zxyono.lego.util.RedisUtil;
import com.zxyono.lego.util.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${lego.token.expirationMilliSeconds}")
    private long expirationMilliSeconds;

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取header中的token信息
        String authToken = request.getHeader("token");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        if (null == authToken){
            filterChain.doFilter(request,response);
            return;
        }

        String subject = JwtTokenUtil.parseToken(authToken);

        //获取redis中的token信息
        if (!redisUtil.hasKey(authToken)){
            //token 不存在 返回错误信息
            response.getWriter().write(JSON.toJSONString(ResultMap.error(HttpStatus.FORBIDDEN, "未登录，或者授权过期")));
            return;
        }

        //获取缓存中的信息
        HashMap<String,Object> hashMap = (HashMap<String, Object>) redisUtil.hget(authToken);

        User user = null;
        Admin admin = null;

        // 表示用户访问
        if (subject.equals("user")) {
            user = new User();
            user.setUserId(Long.parseLong(hashMap.get("userId").toString()));
            user.setAuthorities((Set<? extends GrantedAuthority>) hashMap.get("authorities"));

            request.setAttribute("userId", user.getUserId());
        } else {
            admin = new Admin();
            admin.setAdminId(Long.parseLong(hashMap.get("adminId").toString()));
            admin.setAdminName(hashMap.get("adminName").toString());
            admin.setAuthorities((Set<? extends GrantedAuthority>) hashMap.get("authorities"));

            request.setAttribute("adminId", admin.getAdminId());
            request.setAttribute("adminName", admin.getAdminName());
        }

        if (null == hashMap){
            //用户信息不存在或转换错误，返回错误信息
            response.getWriter().write(JSON.toJSONString(ResultMap.error(HttpStatus.UNAUTHORIZED, "未查询到登录状态")));
            return;
        }

        //更新token过期时间
        redisUtil.setKeyExpire(authToken, expirationMilliSeconds);

        UsernamePasswordAuthenticationToken authenticationToken = null;

        //将信息交给security
        if (subject.equals("user")) {
            authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
        } else {
            authenticationToken = new UsernamePasswordAuthenticationToken(admin,null,admin.getAuthorities());
        }
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);
    }
}