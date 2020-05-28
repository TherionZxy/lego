package com.zxyono.lego.util;

import com.zxyono.lego.entity.History;
import com.zxyono.lego.enums.HistoryEnum;

import javax.servlet.http.HttpServletRequest;

public class HistoryUtil {

    public static History buildLoginHistory(String username, String type) {
        return new History(username, HistoryEnum.A_LOGIN.addMessage(username).getMessage(), type);
    }

    public static History buildLogoutHistory(HttpServletRequest request, String type) {
        String adminName = request.getAttribute("adminName").toString();
        return new History(adminName, HistoryEnum.A_LOGOUT.addMessage(adminName).getMessage(), type);
    }

    public static History buildUpdateHistory(HttpServletRequest request, String type, String ...args) {
        String adminName = request.getAttribute("adminName").toString();
        return new History(adminName, HistoryEnum.A_UPDATE_B.addMessage(adminName).addMessage(args).getMessage(), type);
    }

    public static History buildCreateHistory(HttpServletRequest request, String type, String ...args) {
        String adminName = request.getAttribute("adminName").toString();
        return new History(adminName, HistoryEnum.A_CREATE_B.addMessage(adminName).addMessage(args).getMessage(), type);
    }

    public static History buildDeleteHistory(HttpServletRequest request, String type, String ...args) {
        String adminName = request.getAttribute("adminName").toString();
        return new History(adminName, HistoryEnum.A_DELETE_B.addMessage(adminName).addMessage(args).getMessage(), type);
    }

    public static History buildExportHistory(HttpServletRequest request, String type, String ...args) {
        String adminName = request.getAttribute("adminName").toString();
        return new History(adminName, HistoryEnum.A_EXPORT_B.addMessage(adminName).addMessage(args).getMessage(), type);
    }
}