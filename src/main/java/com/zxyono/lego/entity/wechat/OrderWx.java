package com.zxyono.lego.entity.wechat;

import lombok.Data;

import java.util.Date;

@Data
public class OrderWx {
    private Long orderId;
    private String name;
    private String phone;
    private String status;
    private String code;
    private Date time;
}