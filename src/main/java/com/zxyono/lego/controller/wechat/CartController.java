package com.zxyono.lego.controller.wechat;

import com.zxyono.lego.service.CartService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("list")
    public ResultMap list(HttpServletRequest request) {
        // 从request中获取openid
        String openId = request.getAttribute("openId").toString();

        return cartService.queryCartList(openId);
    }

    @PostMapping("add")
    public ResultMap add(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("num") Integer num) {
        // 从request中获取id
        Long userId = Long.parseLong(request.getAttribute("userId").toString());

        return cartService.addOne(userId, id, num);

    }

    @PostMapping("update")
    public ResultMap update(HttpServletRequest request, @RequestParam("id") Long id, @RequestParam("num") Integer num) {
        // 从request中获取id
        Long userId = Long.parseLong(request.getAttribute("userId").toString());

        return cartService.updateOne(userId, id, num);
    }

    @PostMapping("remove")
    public ResultMap remove(HttpServletRequest request, @RequestParam("id") Long id) {
        // 从request中获取id
        Long userId = Long.parseLong(request.getAttribute("userId").toString());

        return cartService.delOne(userId, id);
    }
}