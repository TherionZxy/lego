package com.zxyono.lego.controller;

import com.zxyono.lego.util.ResultMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysController {

    @RequestMapping(value = "logout/success", method = RequestMethod.GET)
    public ResultMap logoutSuccess() {
        return ResultMap.success("登出成功");
    }
}