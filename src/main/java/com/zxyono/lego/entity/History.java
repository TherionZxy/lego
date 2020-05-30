package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zxyono.lego.enums.HistoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Data
@TableName("tb_history")
@AllArgsConstructor
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

    public History(HttpServletRequest request, HistoryEnum historyEnum, String type, String ...args) {
        String adminName = request.getAttribute("adminName").toString();
        String modelStr = historyEnum.getMessage();

        modelStr = modelStr.replaceFirst("\\{\\}", adminName);

        for (String obj : args) {
            modelStr = modelStr.replaceFirst("\\{\\}", obj);
        }

        this.adminName = adminName;
        this.operation = modelStr;
        this.type = type;
        this.time = new Date();
    }

    public History(String adminName, HistoryEnum historyEnum, String type, String ...args) {
        String modelStr = historyEnum.getMessage();

        modelStr = modelStr.replaceFirst("\\{\\}", adminName);

        for (String obj : args) {
            modelStr = modelStr.replaceFirst("\\{\\}", obj);
        }

        this.adminName = adminName;
        this.operation = modelStr;
        this.type = type;
        this.time = new Date();
    }
}