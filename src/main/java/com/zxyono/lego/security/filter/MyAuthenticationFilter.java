package com.zxyono.lego.security.filter;

import com.zxyono.lego.entity.extend.OpenIdJson;
import com.zxyono.lego.security.token.AdminAuthenticationToken;
import com.zxyono.lego.security.token.WechatAuthenticationToken;
import com.zxyono.lego.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限过滤器
 */
@Slf4j
public class MyAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    @Value("${lego.app.appid}")
    private String appid;

    @Value("${lego.app.appsecret}")
    private String appsecret;

    @Value("${lego.app.authurl}")
    private String authUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        // 根据传递的参数判断是小程序登录还是管理端登录
        // 管理端有两个参数（username、password）
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        // 小程序端只有一个参数（code）
        String code = httpServletRequest.getParameter("code");

        // 优先判断小程序端，如果code不为空，表示是小程序端登录
        if (!StringUtils.isBlank(code)) {
            // 获取openid验证连接
            String url = String.format(authUrl, appid, appsecret, code);
            // 请求微信API接口
            OpenIdJson openIdJson = restTemplate.getForObject(url, OpenIdJson.class);
            // 如果微信API返回错误码，抛出异常
            if (openIdJson.getErrcode() != null && !openIdJson.getErrcode().equals(0)) {
                throw new RuntimeException(openIdJson.getErrmsg());
            }

            return this.getAuthenticationManager().authenticate(new WechatAuthenticationToken(openIdJson.getOpenId(), openIdJson.getSessionKey()));
        } else {
            // 如果code为空，表示是管理端登录
            if (StringUtils.isBlank(username)) {
                throw new RuntimeException("用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                throw new RuntimeException("密码不能为空");
            }
            // 对密码进行转码
            // password = passwordEncoder.encode(password);

            return this.getAuthenticationManager().authenticate(new AdminAuthenticationToken(username, password));

        }
    }
}