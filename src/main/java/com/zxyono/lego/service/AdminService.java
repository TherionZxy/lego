package com.zxyono.lego.service;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.wrapper.AdminWrapper;
import com.zxyono.lego.util.ResultMap;

public interface AdminService {
    public ResultMap adminLogin(String username, String password);

    public ResultMap adminInfo(Long adminId);

    public ResultMap getAdminListWithPage(Integer page, Integer limit, AdminWrapper wrapper);

    public ResultMap getAdminList(AdminWrapper wrapper);

    public ResultMap modifyAdminInfo(Admin admin);

    public ResultMap createAdmin(Admin admin);

    public ResultMap removeAdminById(Long adminId);
}
