package com.zxyono.lego.entity.vo;

import com.zxyono.lego.entity.Admin;
import lombok.Data;

import java.util.List;

@Data
public class AdminVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<Admin> adminList;
}