package com.zxyono.lego.controller;

import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.entity.wrapper.FruitWrapper;
import com.zxyono.lego.service.FruitService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fruit")
public class FruitController {
    @Autowired
    private FruitService fruitService;

    @RequestMapping(value = "get/{page}/{size}", method = RequestMethod.POST)
    private ResultMap getFruitPagesWithParams(@PathVariable("page") Integer page, @PathVariable("size") Integer size,
                                             @RequestBody FruitWrapper params) {
        return ResultMap.success("请求成功", fruitService.queryFruitList(page, size, params));
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    private ResultMap getFruitListWithParams(@RequestBody FruitWrapper params) {
        return ResultMap.success("请求成功",fruitService.queryAllFruitList(params));
    }

    @RequestMapping(value = "get/{fruitId}")
    private ResultMap getFruitById(@PathVariable("fruitId") Long fruitId) {
        return ResultMap.success("请求成功", fruitService.queryFruitById(fruitId));
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    private ResultMap addFruit(@RequestBody Fruit fruit) {
        fruitService.createFruit(fruit);
        return ResultMap.success("新增水果成功");
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    private ResultMap updateFruit(@RequestBody Fruit fruit) {
        fruitService.modifyFruitInfo(fruit);
        return ResultMap.success("修改水果信息成功");
    }

    @RequestMapping(value = "remove/{fruitId}", method = RequestMethod.POST)
    private ResultMap removeFruit(@PathVariable("fruitId") Long fruitId) {
        fruitService.deleteFruitById(fruitId);
        return ResultMap.success("删除水果成功");
    }
}