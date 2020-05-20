package com.zxyono.lego.security.handler;

import cn.hutool.http.ContentType;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.zxyono.lego.security.token.AdminAuthenticationToken;
import com.zxyono.lego.security.token.WechatAuthenticationToken;
import com.zxyono.lego.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 后台管理系统登录成功后的处理类
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 与微信小程序端一样通过JWT进行处理
        Map<String, Object> result = Maps.newHashMap();
        if (authentication instanceof WechatAuthenticationToken) {
            String token = jwtTokenUtils.generateToken(((WechatAuthenticationToken) authentication).getOpenid());
            result.put("wechat-token", token);
        }
        if (authentication instanceof AdminAuthenticationToken) {
            String token = jwtTokenUtils.generateToken(((AdminAuthenticationToken) authentication).getUsername());
            result.put("admin-token", token);
            result.put("roles", authentication.getAuthorities());
        }
        httpServletResponse.setContentType(ContentType.JSON.toString());
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}