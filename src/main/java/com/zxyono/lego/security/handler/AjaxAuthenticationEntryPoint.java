package com.zxyono.lego.security.handler;

import com.alibaba.fastjson.JSON;
import com.zxyono.lego.constant.HttpStatus;
import com.zxyono.lego.util.ResultMap;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(ResultMap.error(HttpStatus.FORBIDDEN, "未登录")));
    }
}