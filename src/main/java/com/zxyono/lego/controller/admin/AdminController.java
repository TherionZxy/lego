package com.zxyono.lego.controller.admin;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.wrapper.AdminWrapper;
import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.DataBaseException;
import com.zxyono.lego.mapper.AdminMapper;
import com.zxyono.lego.service.AdminService;
import com.zxyono.lego.service.HistoryService;
import com.zxyono.lego.util.HistoryUtil;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Resource
    private AdminMapper adminMapper;

    @Autowired
    private HistoryService historyService;

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public ResultMap login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) {
        return adminService.adminLogin(username, password);
    }

    @GetMapping("info")
    public ResultMap info(HttpServletRequest request) {
        Long adminId = Long.parseLong(request.getAttribute("adminId").toString());
        return adminService.adminInfo(adminId);
    }

    @PostMapping("list/{page}/{limit}")
    public ResultMap list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                          @RequestBody AdminWrapper wrapper) {
        return adminService.getAdminListWithPage(page, limit, wrapper);
    }

    @PostMapping("list")
    public ResultMap list(HttpServletRequest request, @RequestBody AdminWrapper wrapper) {
        historyService.createHistory(HistoryUtil.buildExportHistory(request, "user", " 用户管理表 "));
        return adminService.getAdminList(wrapper);
    }

    @PostMapping("update")
    public ResultMap update(HttpServletRequest request, @RequestBody Admin admin) {
        String name = adminMapper.selectById(admin.getAdminId()).getAdminName();
        historyService.createHistory(HistoryUtil.buildUpdateHistory(request, "user", "用户名为 " + name, " 管理员信息"));
        return adminService.modifyAdminInfo(admin);
    }

    @PostMapping("create")
    public ResultMap create(HttpServletRequest request, @RequestBody Admin admin) {
        historyService.createHistory(HistoryUtil.buildCreateHistory(request, "user", "管理员"+admin.getAdminName()));
        return adminService.createAdmin(admin);
    }

    @PostMapping("remove")
    public ResultMap remove(HttpServletRequest request, @RequestParam("adminId") Long adminId) {
        String name = adminMapper.selectById(adminId).getAdminName();
        Long id = Long.parseLong(request.getAttribute("adminId").toString());
        if (id.equals(adminId)) {
            throw new DataBaseException(ExceptionEnum.CANT_DELETE_YOURSELF);
        }

        historyService.createHistory(HistoryUtil.buildDeleteHistory(request, "user", "用户名为 "+name, " 管理员"));

        return adminService.removeAdminById(adminId);
    }


}