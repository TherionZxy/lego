package com.zxyono.lego.controller;

import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.service.OrderItemService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 订单汇总
     * @return
     */
    @RequestMapping(value = "total", method = RequestMethod.GET)
    public ResultMap getTotalOrderList() {
        return ResultMap.success("请求成功", orderItemService.getTotalOrderList());
    }
}