package com.zxyono.lego.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.checkerframework.checker.regex.RegexUtil;

@Getter
@AllArgsConstructor
public enum HistoryEnum {
    A_UPDATE_B("{} 修改了 {} 的 {}"),
    A_CREATE_B("{} 创建了 {}"),
    A_DELETE_B("{} 删除了 {} 的 {}");

    private String message;

    public HistoryEnum addMessage(String ...args) {
        for (String obj : args) {
            this.message = this.message.replaceFirst("\\{\\}", obj);
        }
        return this;
    }

}