package com.zxyono.lego.controller.wechat;

import com.zxyono.lego.entity.wrapper.FruitWrapper;
import com.zxyono.lego.service.FruitService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fruit")
public class FruitController {
    @Autowired
    private FruitService fruitService;

    // 给后台管理用的API
    @PostMapping(value = "list/{page}/{limit}")
    public ResultMap list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                           @RequestBody FruitWrapper wrapper) {
        return fruitService.queryFruitList(page, limit, wrapper);
    }

    @PostMapping(value = "list")
    public ResultMap list(@RequestBody FruitWrapper wrapper) {
        return ResultMap.success("请求成功",fruitService.queryAllFruitList(wrapper));
    }

    @GetMapping(value = "info/{fruitId}")
    public ResultMap info(@PathVariable("fruitId") Long fruitId) {
        return ResultMap.success("请求成功", fruitService.queryFruitById(fruitId));
    }

    //给小程序用的API

    /**
     * 水果条件查询
     * @param searchKey
     * @param activity
     * @return
     */
    @GetMapping(value = "search")
    public ResultMap search(@RequestParam("searchKey") String searchKey, @RequestParam("activity") Integer activity) {
        return fruitService.searchFruitList(searchKey, activity);
    }

    /**
     * 水果详情
     * @param id
     * @return
     */
    @GetMapping(value = "detail")
    public ResultMap detail(@RequestParam("id") Long id) {
        return fruitService.getOne(id);
    }

}