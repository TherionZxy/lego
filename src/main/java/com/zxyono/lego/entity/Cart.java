package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "cart")
public class Cart {
    private Long userId;
    private Long fruitId;
    private Integer fruitNum;
}