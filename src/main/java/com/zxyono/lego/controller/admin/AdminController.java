package com.zxyono.lego.controller.admin;

import com.zxyono.lego.service.AdminService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResultMap login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return adminService.adminLogin(username, password);
    }

    @GetMapping("info")
    public ResultMap info(HttpServletRequest request) {
        Long adminId = Long.parseLong(request.getAttribute("adminId").toString());
        return adminService.adminInfo(adminId);
    }


}