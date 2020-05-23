package com.zxyono.lego.service;

import com.zxyono.lego.entity.History;
import com.zxyono.lego.util.ResultMap;

public interface HistoryService {
    public ResultMap getHistoryListWithPage(Integer page, Integer limit, String username, String type);

    public int createHistory(History history);
}
