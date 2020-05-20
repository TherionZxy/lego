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

    /**
     * 登录授权
     * @param code
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResultMap login(@RequestParam("js_code") String code) {
        OpenIdJson openIdJson = userService.getOpenId(authUrl, appId, appSecret, code);

        if(openIdJson.getErrcode() == null) {
            return ResultMap.success("登录成功", userService.createToken(openIdJson));
        } else {
            // 微信auth API返回的错误码以及错误信息
            if (openIdJson.getErrcode() == -1) {
                return ResultMap.error("系统繁忙");
            } else if (openIdJson.getErrcode() == 40029) {
                return ResultMap.error("code 无效");
            } else if (openIdJson.getErrcode() == 45011) {
                return ResultMap.error("请求过于频繁");
            } else if (openIdJson.getErrcode() == 40163) {
                return ResultMap.error("code已被使用");
            } else {
                return ResultMap.error("openId接口未知异常");
            }
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResultMap updateUser(HttpServletRequest httpServletRequest, @RequestBody User params) {
        // 暂时将获取请求头参数写在controller中，未来将写在拦截器中
        String token = httpServletRequest.getHeader("TOKEN");
        if (token == null) {
            return ResultMap.error("请求头中未包含访问令牌");
        } else {
            String openId = userService.getOpenIdByToken(token);
            if (openId == null) {
                return ResultMap.error("令牌错误或者令牌已过期，请重新登录");
            } else {
                params.setOpenId(openId);
                userService.modifyUserInfo(params);
                return ResultMap.success("修改用户信息成功");
            }
        }
    }

}