package com.zxyono.lego.controller.wechat;

import com.zxyono.lego.service.FruitService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fruit")
public class FruitController {
    @Autowired
    private FruitService fruitService;

    @GetMapping(value = "list/{page}/{size}")
    private ResultMap list(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                           @RequestParam("fruitname") String fruitName, @RequestParam("status1") Integer isSale,
                           @RequestParam("status2") Integer isFlashSale) {
        return fruitService.queryFruitList(page, size, fruitName, isSale, isFlashSale);
    }

    @GetMapping(value = "list")
    private ResultMap list(@RequestParam("fruitname") String fruitName, @RequestParam("status1") Integer isSale,
                           @RequestParam("status2") Integer isFlashSale) {
        return ResultMap.success("请求成功",fruitService.queryAllFruitList(fruitName, isSale, isFlashSale));
    }

    @GetMapping(value = "info/{fruitId}")
    private ResultMap info(@PathVariable("fruitId") Long fruitId) {
        return ResultMap.success("请求成功", fruitService.queryFruitById(fruitId));
    }


}