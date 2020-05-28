package com.zxyono.lego.entity.vo;

import com.zxyono.lego.entity.extend.TotalOrder;
import lombok.Data;

import java.util.List;

@Data
public class TotalOrderVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<TotalOrder> totalList;
}