package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.Cart;
import com.zxyono.lego.mapper.CartMapper;
import com.zxyono.lego.service.CartService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private CartMapper cartMapper;

    @Override
    public ResultMap queryCartList(String openid) {
        return ResultMap.success(cartMapper.getCartWxList(openid));
    }

    @Override
    public ResultMap addOne(Long userId ,Long id, Integer num) {
        Cart cart = new Cart();
        cart.setFruitId(id);
        cart.setFruitNum(num);
        cart.setUserId(userId);
        return ResultMap.success(cartMapper.insert(cart));
    }

    @Override
    public ResultMap delOne(Long userId, Long id) {
        return ResultMap.success(cartMapper.deleteById(userId, id));
    }

    @Override
    public ResultMap updateOne(Long userId, Long id, Integer num) {
        return ResultMap.success(cartMapper.updateById(userId, id, num));
    }
}