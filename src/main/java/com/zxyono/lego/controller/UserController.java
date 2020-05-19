package com.zxyono.lego.controller;

import com.zxyono.lego.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping(value = "set/{value}", method = RequestMethod.GET)
    public String test(@PathVariable String value) {
        User user = new User();
        user.setUserId(1L);
        user.setOpenId("zxyono");
        user.setDefaultName("小野");
        user.setDefaultPhone("15871384790");
        redisTemplate.opsForValue().set("key",user);
        return "success";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get() {
        return redisTemplate.opsForValue().get("key").toString();
    }
}