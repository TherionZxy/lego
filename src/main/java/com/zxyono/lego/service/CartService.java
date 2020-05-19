package com.zxyono.lego.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zxyono.lego.entity.Cart;

public interface CartService {
    public IPage<Cart> queryCartList(Integer page, Integer size);
}
