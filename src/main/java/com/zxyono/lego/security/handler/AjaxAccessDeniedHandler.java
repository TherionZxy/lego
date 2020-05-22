package com.zxyono.lego.security.handler;

import com.alibaba.fastjson.JSON;
import com.zxyono.lego.constant.HttpStatus;
import com.zxyono.lego.util.ResultMap;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(ResultMap.error(HttpStatus.ERROR, "拒绝访问")));
    }
}