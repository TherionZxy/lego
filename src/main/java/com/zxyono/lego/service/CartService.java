package com.zxyono.lego.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxyono.lego.entity.Cart;
import com.zxyono.lego.util.ResultMap;

public interface CartService {
    public ResultMap queryCartList(String openid);

    public ResultMap addOne(Long userId, Long id, Integer num);

    public ResultMap delOne(Long userId, Long id);

    public ResultMap updateOne(Long userId, Long id, Integer num);
}
