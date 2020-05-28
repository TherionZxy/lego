package com.zxyono.lego.controller.admin;

import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.service.FruitService;
import com.zxyono.lego.service.HistoryService;
import com.zxyono.lego.util.HistoryUtil;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/admin/fruit")
public class FruitAdminController {
    @Autowired
    private FruitService fruitService;
    @Autowired
    private HistoryService historyService;

    @PostMapping(value = "add")
    private ResultMap addFruit(HttpServletRequest request, @RequestBody Fruit fruit) {
        fruitService.createFruit(fruit);
        historyService.createHistory(HistoryUtil.buildCreateHistory(request, "fruit", "水果\"" + fruit.getFruitName() + "\""));
        return ResultMap.success("新增水果成功");
    }

    @PostMapping(value = "update")
    private ResultMap updateFruit(HttpServletRequest request, @RequestBody Fruit fruit) {
       fruitService.modifyFruitInfo(fruit);
       historyService.createHistory(HistoryUtil.buildUpdateHistory(request, "fruit", "水果\"" + fruit.getFruitName() + "\"", "水果信息"));
        return ResultMap.success("修改水果信息成功");
    }

    @PostMapping(value = "updateFlash")
    private ResultMap updateFlash(@RequestBody Fruit fruit) {
        fruitService.modifyFlashSaleInfo(fruit);
        return ResultMap.success("修改抢购信息成功");
    }

    @PostMapping(value = "removeFlash")
    private ResultMap removeFlash(@RequestParam("fruitId") Long fruitId) {
        fruitService.removeFruitFromFlashSale(fruitId);
        return ResultMap.success("移除抢购商品成功");
    }

    @PostMapping(value = "stopFlash")
    private ResultMap stopFlash(@RequestParam("fruitId") Long fruitId) {
        fruitService.modifyFlashSaleStatus(fruitId);
        return ResultMap.success("强制停止限时抢购成功");
    }

    @PostMapping(value = "remove")
    private ResultMap removeFruit(@RequestParam("fruitId") Long fruitId) {
        fruitService.deleteFruitById(fruitId);
        return ResultMap.success("删除水果成功");
    }


}