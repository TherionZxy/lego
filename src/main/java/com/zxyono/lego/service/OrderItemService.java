package com.zxyono.lego.service;

import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.entity.wrapper.TotalOrderWrapper;
import com.zxyono.lego.util.ResultMap;

import java.util.List;

public interface OrderItemService {
    /**
     * 获取汇总后的订单
     * @return
     */
    public ResultMap getTotalOrderListWithPage(Integer page, Integer limit, TotalOrderWrapper wrapper);

    public ResultMap getTotalOrderList(TotalOrderWrapper wrapper);

}

