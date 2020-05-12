package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("fruit")
public class Fruit {
    @TableId
    private Long fruitId;
    private String fruitName;
    private Double normPrice;
    private Double discountPrice;
    private String fruitIntro;
    private String fruitPic;
    private int isFlashSale;
    private int maxNum;
    private Date startTime;
    private Date endTime;
}