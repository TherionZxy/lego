package com.zxyono.lego.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_role")
public class Role {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
    private Long adminId;
    private String roleName;
}