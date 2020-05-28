package com.zxyono.lego.entity.wechat;

import lombok.Data;

@Data
public class CartWx {
    private Long id;
    private String display;
    private String intro;
    private String name;
    private Double price;
    private Double origin;
    private String desc;
    private Integer num;
}