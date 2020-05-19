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

    /**
     * 通过code获取用户openid以及session_key等信息
     * @param authUrl
     * @param appId
     * @param appSecret
     * @param code
     * @return
     */
    public OpenIdJson getOpenId(String authUrl, String appId, String appSecret, String code);

    /**
     * 创建登录态
     * @param openIdJson
     * @return
     */
    public String createToken(OpenIdJson openIdJson);

    /**
     * 根据小程序端每次请求附带的token查询用户的openId
     * @param token
     * @return
     */
    public String getOpenIdByToken(String token);
}
