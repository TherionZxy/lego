package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_cart")
public class Cart {
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Long cartId;
    private Long userId;
    private Long fruitId;
    private Integer fruitNum;
}