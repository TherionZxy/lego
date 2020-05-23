package com.zxyono.lego.controller.admin;

import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.service.FruitService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/fruit")
public class FruitAdminController {
    @Autowired
    private FruitService fruitService;

    @PostMapping(value = "add")
    private ResultMap addFruit(@RequestBody Fruit fruit) {
        fruitService.createFruit(fruit);
        return ResultMap.success("新增水果成功");
    }

    @PostMapping(value = "update")
    private ResultMap updateFruit(@RequestBody Fruit fruit) {
        fruitService.modifyFruitInfo(fruit);
        return ResultMap.success("修改水果信息成功");
    }

    @PostMapping(value = "updateFlash")
    private ResultMap updateFlash(@RequestBody Fruit fruit) {
        fruitService.modifyFlashSaleInfo(fruit);
        return ResultMap.success("修改抢购信息成功");
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