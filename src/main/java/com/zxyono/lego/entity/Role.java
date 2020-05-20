package com.zxyono.lego.entity;

import lombok.Data;

@Data
public class Role {
    private Long roleId;
    private Long adminId;
    private String roleName;
}