//package com.zxyono.lego.config;
//
//import com.zxyono.lego.security.provider.AdminAuthenticationProvider;
//import com.zxyono.lego.service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfigBak extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    AdminService adminService;
//
//    @Bean
//    public AdminAuthenticationProvider adminAuthenticationProvider() {
//        return new AdminAuthenticationProvider();
//    }
//
//    /**
//     * 使用BCryptPasswordEncoder加密密码
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                if (s == null) {
//                    return false;
//                } else {
//                    return s.equals(charSequence.toString());
//                }
//            }
//        };
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////            .userDetailsService(adminService).passwordEncoder(passwordEncoder());
//        /**
//         * Admin 登录Provider
//         */
//        auth
//            .authenticationProvider(adminAuthenticationProvider())
//            .userDetailsService(adminService)
//            .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin()
//                .loginPage("/admin/login")
//                .permitAll()
//                // 登录成功后，不返回页面url而是返回json信息
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        List<String> roles = new ArrayList<>();
//                        for (GrantedAuthority authority: authentication.getAuthorities()) {
//                            roles.add("\"" + authority.getAuthority().split("_")[1] + "\"");
//                        }
//                        // 获取权限信息
//                        String username = authentication.getName();
//                        out.write("{\"code\":20000, \"msg\":\"登录成功\", \"token\":{\"name\":\""+username+"\",\"roles\":"+roles+"}}");
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        out.write("{\"code\":\"500\", \"msg\":\"登录失败\"}");
//                        out.flush();
//                        out.close();
//                    }
//                }).loginProcessingUrl("/admin/login")
//                .and()
//            .authorizeRequests()
//                // 设置将哪些页面排除在权限控制之外
//                .antMatchers("/", "/home").permitAll()
//                // 设置剩下的页面全部需要进行权限控制
//                .anyRequest().authenticated()
//                .and()
//            .logout()
//                .permitAll()
//                .and()
//            // 关闭跨站请求伪造检测
//            .csrf().disable();
//    }
//}