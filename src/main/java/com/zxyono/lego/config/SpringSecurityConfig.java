package com.zxyono.lego.config;

import com.zxyono.lego.security.MyAuthenticationManager;
import com.zxyono.lego.security.filter.JwtAuthenticationTokenFilter;
import com.zxyono.lego.security.filter.MyAuthenticationFilter;
import com.zxyono.lego.security.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationManager myAuthenticationManager;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyAuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    @Bean
    public MyAuthenticationFilter wechatAuthenticationFilter() {
        MyAuthenticationFilter myAuthenticationFilter = new MyAuthenticationFilter("/login");
        myAuthenticationFilter.setAuthenticationManager(myAuthenticationManager);
        myAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler());
        return myAuthenticationFilter;
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .headers().frameOptions().sameOrigin().and()
            .authorizeRequests()
                .antMatchers("/error/**","/h2-console/**").permitAll()
                .anyRequest().authenticated();
        http
            // 使用wechatAuthenticationFilter替换默认的认证过滤器UsernamePasswordAuthenticationFilter
            .addFilterAt(wechatAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(jwtAuthenticationTokenFilter(), MyAuthenticationFilter.class);

    }
}