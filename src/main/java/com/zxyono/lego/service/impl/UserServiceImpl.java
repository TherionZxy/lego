package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zxyono.lego.entity.User;
import com.zxyono.lego.mapper.UserMapper;
import com.zxyono.lego.repository.RedisRepository;
import com.zxyono.lego.service.UserService;
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

}