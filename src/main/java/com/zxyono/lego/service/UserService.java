package com.zxyono.lego.service;

import com.zxyono.lego.entity.User;
import com.zxyono.lego.entity.extend.OpenIdJson;

public interface UserService {
    /**
     * 根据Id查询用户信息
     * @param userId
     * @return
     */
    public User queryUserById(Long userId);

    /**
     * 根据OpenId查询用户信息
     * @param openId
     * @return
     */
    public User queryUserByOpenId(String openId);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    public Integer createUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public Integer modifyUserInfo(User user);

}
