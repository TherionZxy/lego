package com.zxyono.lego.controller.wechat;

import com.zxyono.lego.entity.wrapper.OrderItemsWrapper;
import com.zxyono.lego.entity.wrapper.OrderWrapper;
import com.zxyono.lego.service.OrderService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("list/{page}/{limit}")
    public ResultMap list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                          @RequestBody OrderWrapper wrapper) {
        return orderService.queryOrderList(page, limit, wrapper);
    }

    @PostMapping("list")
    public ResultMap list(@RequestBody OrderWrapper wrapper) {
        return orderService.queryAllOrderList(wrapper);
    }

    @GetMapping("nums")
    public ResultMap nums() {
        return orderService.queryOrderNums();
    }

    @GetMapping("gets")
    public ResultMap gets(HttpServletRequest request, @RequestParam("status") Integer status) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        // status = -1时表示查询用户所有订单
        if (status == null) {
            return orderService.queryOrderByUserId(userId);
        } else {
            return orderService.queryOrderByUserIdWithStatus(userId, status);
        }
    }

    @PostMapping("create")
    public ResultMap create(HttpServletRequest request, @RequestBody OrderItemsWrapper wrapper) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        return orderService.createOrder(wrapper, userId);
    }

    @PostMapping("payed")
    public ResultMap payed(HttpServletRequest request, @RequestParam("orderId") Long orderId) {
        Long userId = Long.parseLong(request.getAttribute("userId").toString());
        return orderService.updateOrderStatusToPayed(userId, orderId);
    }
}