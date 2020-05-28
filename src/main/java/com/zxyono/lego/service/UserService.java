package com.zxyono.lego.service;

import com.zxyono.lego.entity.User;
import com.zxyono.lego.entity.extend.OpenIdJson;
import com.zxyono.lego.util.ResultMap;

public interface UserService {
    /**
     * 根据Id查询用户信息
     * @param userId
     * @return
     */
    public ResultMap queryUserById(Long userId);

    public ResultMap wechatLogin(String code);

    public ResultMap updateUserInfo(Long userId, String phone, String name);

}
