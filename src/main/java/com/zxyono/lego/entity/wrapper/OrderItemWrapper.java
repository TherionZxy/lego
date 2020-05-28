package com.zxyono.lego.entity.wrapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderItemWrapper {
    private Long orderId;
    // 水果id
    private Long id;
    // 数量
    private Integer number;
    // 价格
    private Double price;
}