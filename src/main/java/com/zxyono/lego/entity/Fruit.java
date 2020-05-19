package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fruit")
public class Fruit {
    @TableId(type = IdType.AUTO)
    private Long fruitId;
    private String fruitName;
    private Double normPrice;
    private Double discountPrice;
    private String fruitIntro;
    private String fruitPic;
    private Integer isFlashSale;
    private Integer isSale;
    private Integer maxNum;
    private Date startTime;
    private Date endTime;
}