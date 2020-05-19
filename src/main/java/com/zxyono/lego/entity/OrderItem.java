package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_item")
public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long fruitId;
    private Integer fruitNum;
    private Double fruitPrice;
}