package com.zxyono.lego.service;

import com.zxyono.lego.entity.Order;
import com.zxyono.lego.entity.wrapper.OrderItemsWrapper;
import com.zxyono.lego.entity.wrapper.OrderWrapper;
import com.zxyono.lego.util.ResultMap;

public interface OrderService {
    // 给后台管理使用的查询方法
    public ResultMap queryOrderList(Integer page, Integer limit, OrderWrapper wrapper);

    public ResultMap queryAllOrderList(OrderWrapper wrapper);

    public ResultMap queryOrderNums();

    public ResultMap queryOrderById(Long orderId);

    public ResultMap queryOrderByUserId(Long userId);

    public ResultMap queryOrderByUserIdWithStatus(Long userId, Integer status);

    public ResultMap createOrder(OrderItemsWrapper wrappers, Long userId);

    public ResultMap deleteOrderById(Long orderId);

    public ResultMap updateOrderStatusToComplete(Long orderId);

    public ResultMap updateOrderStatusToPayed(Long userId, Long orderId);
}
