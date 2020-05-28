package com.zxyono.lego.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用来进行登录状态保存，限制同时同一个管理员只能在一个电脑上登录
 */
public class AdminLoginMap {

    private HashMap<Long, String> map;

    public AdminLoginMap() {
        this.map = new HashMap<>();
    }

    public String find(Long adminId) {
        if (this.map.containsKey(adminId)) {
            return this.map.get(adminId);
        } else {
            return null;
        }
    }

    public void set(Long adminId, String token) {
        this.map.put(adminId, token);
    }

    public String findAndSet(Long adminId, String token) {
        if (this.map.containsKey(adminId)) {
            String temp = this.map.get(adminId);
            this.set(adminId, token);

            return temp;
        } else {
            this.set(adminId, token);
            return null;
        }
    }
}