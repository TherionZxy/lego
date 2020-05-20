package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxyono.lego.entity.User;
import com.zxyono.lego.entity.extend.OpenIdJson;
import com.zxyono.lego.exception.JsonParseException;
import com.zxyono.lego.mapper.UserMapper;
import com.zxyono.lego.repository.RedisRepository;
import com.zxyono.lego.service.UserService;
import com.zxyono.lego.util.HttpUtils;
import com.zxyono.lego.util.SessionKeyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisRepository redisRepository;

    @Override
    public User queryUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public User queryUserByOpenId(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public Integer createUser(User user) {
        user.setUserId(null);
        return userMapper.insert(user);
    }

    @Override
    public Integer modifyUserInfo(User user) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .set(user.getDefaultName() != null, "default_name", user.getDefaultName())
                .set(user.getDefaultPhone() != null, "default_phone", user.getDefaultPhone())
                .eq("open_id", user.getOpenId());
        return userMapper.update(null, userUpdateWrapper);
    }

    @Override
    public OpenIdJson getOpenId(String authUrl, String appId, String appSecret, String code) {
        String jsonResult = HttpUtils.doGet(authUrl + "?appid=" + appId + "&secret=" + appSecret + "&js_code="+code+
                "&grant_type=authorization_code",null);
        // 解析得到的json数据
        ObjectMapper objectMapper = new ObjectMapper();
        OpenIdJson openIdJson = null;
        try {
            openIdJson = objectMapper.readValue(jsonResult, OpenIdJson.class);
        } catch (JsonProcessingException e) {
            throw new JsonParseException();
        }
        return openIdJson;
    }

    @Override
    public String createToken(OpenIdJson openIdJson) {
        // 第一步：查询user数据库中是否存在该用户信息
        User user = queryUserByOpenId(openIdJson.getOpenId());
        // 1.1 如果不存在，创建新用户保存到数据库中
        if(user == null) {
            user = new User();
            user.setOpenId(openIdJson.getOpenId());
            createUser(user);
        }
        // 第二步：创建唯一的UUID作为Redis中的键，值为openId
        String token = SessionKeyUtils.createToken();
        // 第三步：将创建的token连同openId一起存入Redis中
        try {
            redisRepository.put(token, openIdJson.getOpenId());
        } catch (Exception e) {
            throw new RedisConnectException();
        }
        // 第四步：返回token作为用户访问令牌
        return token;
    }

    @Override
    public String getOpenIdByToken(String token) {
        String openId = null;
        try {
            openId = redisRepository.get(token);
        } catch (Exception e) {
            throw new RedisConnectException();
        }
        return openId;
    }
}