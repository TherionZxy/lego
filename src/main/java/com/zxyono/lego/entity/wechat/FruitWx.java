package com.zxyono.lego.entity.wechat;

import lombok.Data;

import java.util.Date;

/**
 * 给小程序的返回实体
 */
@Data
public class FruitWx {
    private Long id;
    private String name;
    private String desc;
    private Integer display;
    private Integer intro;
    private Double price;
    private Double origin;
    private Integer status;
    private Date start;
    private Date end;
}