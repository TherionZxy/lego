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
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${lego.app.appid}")
    private String appId;

    @Value("${lego.app.appsecret}")
    private String appSecret;

    @Value("${lego.app.authurl}")
    private String authUrl;

    /**
     * 小程序端登录（包含注册，所以小程序端没有注册）
     * @param code
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    public ResultMap login(@RequestParam("code") String code) throws Exception {
        return userService.wechatLogin(code);
    }
}