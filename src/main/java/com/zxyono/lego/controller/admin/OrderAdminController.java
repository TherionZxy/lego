package com.zxyono.lego.controller.admin;

import com.zxyono.lego.service.OrderService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin/order")
public class OrderAdminController {
    @Autowired
    private OrderService orderService;

    @PostMapping("complete")
    public ResultMap complete(@RequestParam("orderId") Long orderId) {
        return orderService.updateOrderStatusToComplete(orderId);
    }
}