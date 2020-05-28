package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_history")
public class History {
    @TableId(value = "history_id", type = IdType.AUTO)
    private Long historyId;
    private String adminName;
    private String operation;
    private String type;
    private Date time;

    public History(String adminName, String operation, String type) {
        this.adminName = adminName;
        this.operation = operation;
        this.type = type;
        this.time = new Date();
    }
}