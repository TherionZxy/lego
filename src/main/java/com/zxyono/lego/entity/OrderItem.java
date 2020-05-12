package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_item")
public class OrderItem {
    private Long orderId;
    private Long fruitId;
    private int fruitNum;
    private Double fruitPrice;
}