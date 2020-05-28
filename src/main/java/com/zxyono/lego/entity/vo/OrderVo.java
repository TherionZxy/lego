package com.zxyono.lego.entity.vo;

import com.zxyono.lego.entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<Order> orderList;
}