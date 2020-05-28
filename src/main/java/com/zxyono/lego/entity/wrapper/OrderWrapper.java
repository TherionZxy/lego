package com.zxyono.lego.entity.wrapper;

import lombok.Data;

import java.util.Date;

@Data
public class OrderWrapper {
    private String name;
    private String phone;
    private String code;
    private Integer status;
    private Date startTime;
    private Date endTime;
}