package com.zxyono.lego.entity.vo;

import com.zxyono.lego.entity.History;
import lombok.Data;

import java.util.List;

@Data
public class HistoryVo {
    private Integer page;
    private Integer size;
    private Long total;
    private List<History> historyList;
}