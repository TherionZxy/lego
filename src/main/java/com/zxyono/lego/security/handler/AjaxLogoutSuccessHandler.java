package com.zxyono.lego.security.handler;

import com.alibaba.fastjson.JSON;
import com.zxyono.lego.constant.HttpStatus;
import com.zxyono.lego.util.RedisUtil;
import com.zxyono.lego.util.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取token
        String authToken = request.getHeader("token");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // 首先查找redis中是否有此token
        if (redisUtil.hasKey(authToken)) {
            redisUtil.deleteKey(authToken);
            response.getWriter().write(JSON.toJSONString(ResultMap.success("登出成功")));
        } else {
            response.getWriter().write(JSON.toJSONString(ResultMap.error(HttpStatus.BAD_REQUEST, "该账号未登录")));
        }

    }
}