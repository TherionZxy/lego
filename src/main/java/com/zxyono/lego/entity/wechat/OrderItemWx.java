package com.zxyono.lego.entity.wechat;

import lombok.Data;

@Data
public class OrderItemWx {
    private Long fruitId;
    private Double price;
    private String fruitname;
    private Integer number;
}