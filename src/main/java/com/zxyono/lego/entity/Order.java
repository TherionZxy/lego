package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zxyono.lego.entity.wechat.OrderItemWx;
import com.zxyono.lego.entity.wechat.OrderWx;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("tb_order")
public class Order {
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;
    @JsonIgnore
    private Long userId;
    private String code;
    @JsonProperty(value = "status")
    private Integer orderStatus;
    @JsonProperty(value = "content")
    private String orderContent;
    @JsonProperty(value = "name")
    private String orderOwner;
    @JsonProperty(value = "phone")
    private String orderPhone;
    @JsonProperty(value = "time")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date createTime;

    @TableField(exist = false)
    @JsonProperty(value = "fruits")
    private List<OrderItemWx> orderItems;
}