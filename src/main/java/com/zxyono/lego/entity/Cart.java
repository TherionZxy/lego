package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cart")
public class Cart {
    private Long userId;
    private Long fruitId;
    private int fruitNum;
}