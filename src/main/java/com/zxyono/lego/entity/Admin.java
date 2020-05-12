package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {
    @TableId
    private Long adminId;
    private String adminName;
    private String adminPwd;
    private String adminPhone;
    private String adminSess;
}