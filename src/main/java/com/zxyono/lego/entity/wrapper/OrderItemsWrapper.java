package com.zxyono.lego.entity.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class OrderItemsWrapper {
    // 下订单用户设置的名称
    private String name;
    // 下订单用户设置的手机号
    private String phone;

    // 订单列表项
    private List<OrderItemWrapper> fruits;

}