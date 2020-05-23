package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("fruit")
public class Fruit {
    @TableId(value = "fruit_id",type = IdType.AUTO)
    private Long fruitId;
    private String fruitName;
    private Double normPrice;
    private Double discountPrice;
    private String fruitIntro;
    private String fruitPic;
    private Integer isFlashSale;
    private Integer isSale;
    private Integer maxNum;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date startTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm")
    private Date endTime;
}