package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "test")
public class TestObject {
    private String name;
    private String msg;
}