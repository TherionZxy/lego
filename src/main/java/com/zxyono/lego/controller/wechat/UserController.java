package com.zxyono.lego.controller.wechat;

import com.zxyono.lego.entity.User;
import com.zxyono.lego.entity.extend.OpenIdJson;
import com.zxyono.lego.service.UserService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${lego.app.appid}")
    private String appId;

    @Value("${lego.app.appsecret}")
    private String appSecret;

    @Value("${lego.app.authurl}")
    private String authUrl;


}