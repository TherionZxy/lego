package com.zxyono.lego.service;

import com.zxyono.lego.entity.extend.TotalOrder;

import java.util.List;

public interface OrderItemService {
    /**
     * 获取汇总后的订单
     * @return
     */
    public List<TotalOrder> getTotalOrderList();

}

