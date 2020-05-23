package com.zxyono.lego.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxyono.lego.entity.User;
import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.JwtException;
import com.zxyono.lego.exception.WechatException;
import com.zxyono.lego.mapper.UserMapper;
import com.zxyono.lego.service.UserService;
import com.zxyono.lego.util.Jcode2SessionUtil;
import com.zxyono.lego.util.JwtTokenUtil;
import com.zxyono.lego.util.RedisUtil;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Value("${lego.app.appid}")
    private String appid;

    @Value("${lego.app.appsecret}")
    private String appsecret;

    @Value("${lego.app.authurl}")
    private String authurl;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${lego.token.expirationMilliSeconds}")
    private long expirationMilliSeconds;

    @Override
    public ResultMap wechatLogin(String code) {
        JSONObject sessionInfo = JSONObject.parseObject(jcode2Session(code));

        if (sessionInfo == null) {
            throw new WechatException(ExceptionEnum.BAD_REQUEST_FOR_WECHAT_URL);
        } else if (sessionInfo.getInteger("errcode") != null && sessionInfo.getInteger("errcode") != 0) {
            Integer errcode = sessionInfo.getInteger("errcode");
            System.out.println(errcode);
            if (ExceptionEnum.OPENID_URL_INVALID.getCode().equals(errcode)) {
                throw new WechatException(ExceptionEnum.OPENID_URL_INVALID);
            } else if (ExceptionEnum.JS_CODE_INVALID.getCode().equals(errcode)) {
                throw new WechatException(ExceptionEnum.JS_CODE_INVALID);
            } else if (ExceptionEnum.JS_CODE_USED.getCode().equals(errcode)) {
                throw new WechatException(ExceptionEnum.JS_CODE_USED);
            } else {
                throw new WechatException(ExceptionEnum.REQUEST_OPENID_FAIL, sessionInfo.getString("errmsg"));
            }
        }

        // 获取用户唯一标识符 openid成功
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id", sessionInfo.getString("openid")));

        // 该用户在数据库中不存在
        if (user == null) {
            user = new User();
            user.setOpenId(sessionInfo.getString("openid"));
            int result = userMapper.insert(user);
        }

        Set authoritiesSet = new HashSet();
        authoritiesSet.add(new SimpleGrantedAuthority("USER"));
        user.setAuthorities(authoritiesSet);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("userId",user.getUserId().toString());
        hashMap.put("authorities",authoritiesSet);

        String token = JwtTokenUtil.generateToken(user, expirationMilliSeconds);
        redisUtil.hset(token, hashMap);

        return ResultMap.success("用户登录成功", token);
    }

    private String jcode2Session(String code) {
        String sessionInfo = null;
        try {
            sessionInfo  = Jcode2SessionUtil.jscode2Session(appid,appsecret,authurl,code,"authorization_code");//登录grantType固定
        } catch (Exception e) {
            throw new JwtException(ExceptionEnum.JWT_CREATE_EXCEPTION);
        }
        return sessionInfo;
    }



    //    @Override
//    public User queryUserById(Long userId) {
//        return userMapper.selectById(userId);
//    }
//
//    @Override
//    public User queryUserByOpenId(String openId) {
//        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
//    }
//
//    @Override
//    public Integer createUser(User user) {
//        user.setUserId(null);
//        return userMapper.insert(user);
//    }
//
//    @Override
//    public Integer modifyUserInfo(User user) {
//        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
//        userUpdateWrapper
//                .set(user.getDefaultName() != null, "default_name", user.getDefaultName())
//                .set(user.getDefaultPhone() != null, "default_phone", user.getDefaultPhone())
//                .eq("open_id", user.getOpenId());
//        return userMapper.update(null, userUpdateWrapper);
//    }



}