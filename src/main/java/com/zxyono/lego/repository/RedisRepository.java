package com.zxyono.lego.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class RedisRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存字符型数据到Redis数据库中
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 取出保存在Redis数据库中的字符型数据
     * @param key
     * @return
     */
    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

}