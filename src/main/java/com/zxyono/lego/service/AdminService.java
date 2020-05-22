package com.zxyono.lego.service;

import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.util.ResultMap;

public interface AdminService {
    public ResultMap adminLogin(String username, String password);

    public ResultMap adminInfo(Long adminId);
}
