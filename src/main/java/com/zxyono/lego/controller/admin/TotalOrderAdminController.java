package com.zxyono.lego.controller.admin;

import com.zxyono.lego.entity.wrapper.TotalOrderWrapper;
import com.zxyono.lego.service.OrderItemService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/total")
public class TotalOrderAdminController {
    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("list/{page}/{limit}")
    public ResultMap list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit, @RequestBody TotalOrderWrapper wrapper) {
        return orderItemService.getTotalOrderListWithPage(page, limit, wrapper);
    }

    @PostMapping("list")
    public ResultMap list(@RequestBody TotalOrderWrapper wrapper) {
        return orderItemService.getTotalOrderList(wrapper);
    }
}