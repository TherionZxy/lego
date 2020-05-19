package com.zxyono.lego.util;

import java.util.UUID;

public class SessionKeyUtils {
    /**
     * 使用UUID创建访问令牌
     * @return
     */
    public static String createToken() {
        // 通过UUID创建访问令牌，可以保证唯一性
        return UUID.randomUUID().toString().replace("-","");
    }
}