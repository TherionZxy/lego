package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId
    private Long userId;
    private String openId;
    private String defaultPhone;
    private String defaultName;

}