package com.zxyono.lego.service;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.util.ResultMap;

public interface AdminService {
    public ResultMap adminLogin(String username, String password);

    public ResultMap adminInfo(Long adminId);

    public ResultMap getAdminListWithPage(Integer page, Integer limit, String username, String phone, String sort);

    public ResultMap getAdminList(String username, String phone, String sort);

    public ResultMap modifyAdminInfo(Admin admin);

    public ResultMap createAdmin(Admin admin);

    public ResultMap removeAdminById(Long adminId);
}
