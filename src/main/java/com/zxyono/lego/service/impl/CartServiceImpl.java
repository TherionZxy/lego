package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.Cart;
import com.zxyono.lego.mapper.CartMapper;
import com.zxyono.lego.service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;

    @Override
    public IPage<Cart> queryCartList(Integer page, Integer size) {
        return cartMapper.selectPage(new Page<>(page, size), null);
    }
}