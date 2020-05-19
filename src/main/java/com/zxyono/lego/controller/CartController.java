//package com.zxyono.lego.controller;
//
//import com.zxyono.lego.service.CartService;
//import com.zxyono.lego.util.ResultMap;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("cart")
//public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @RequestMapping(value = "get/{page}/{size}", method = RequestMethod.POST)
//    public ResultMap getCartPages(@PathVariable("page") Integer page,@PathVariable("size") Integer size) {
//        return ResultMap.success(cartService.queryCartList(page, size));
//    }
//}