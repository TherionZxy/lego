package com.zxyono.lego.controller;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.util.ResultMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    /**
     * 后台管理系统登录API
     * @param admin
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultMap login(@RequestBody Admin admin) {
        return ResultMap.success();
    }

    /**
     * 获取管理员个人信息
     */
    @RequestMapping(value = "getInfo", method = RequestMethod.GET)
    public ResultMap getInfo() {
        return ResultMap.success();
    }
}
