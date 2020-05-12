package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("order")
public class Order {
    @TableId
    private Long orderId;
    private Long userId;
    private int orderStatus;
    private String orderContent;
    private String orderOwner;
    private String orderPhone;
    private Date createTime;
}