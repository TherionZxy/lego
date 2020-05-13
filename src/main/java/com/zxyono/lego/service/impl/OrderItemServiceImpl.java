package com.zxyono.lego.service.impl;

import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.mapper.OrderItemMapper;
import com.zxyono.lego.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<TotalOrder> getTotalOrderList() {
        return orderItemMapper.queryTotalOrderList();
    }
}