package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.History;
import com.zxyono.lego.entity.vo.HistoryVo;
import com.zxyono.lego.mapper.HistoryMapper;
import com.zxyono.lego.service.HistoryService;
import com.zxyono.lego.util.ResultMap;
import com.zxyono.lego.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;

    @Override
    public ResultMap getHistoryListWithPage(Integer page, Integer limit, String username, String type) {
        HistoryVo historyVo = new HistoryVo();
        IPage<History> iPage = new Page<>(page, limit);

        QueryWrapper<History> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(username), "admin_name", username)
                .eq(StringUtils.isNotEmpty(type), "type", type)
                .orderByDesc("time");
        historyMapper.selectPage(iPage, queryWrapper);

        historyVo.setPage(page);
        historyVo.setSize(limit);
        historyVo.setTotal(iPage.getTotal());
        historyVo.setHistoryList(iPage.getRecords());

        return ResultMap.success(historyVo);
    }

    @Override
    public int createHistory(History history) {
        history.setHistoryId(null);
        return historyMapper.insert(history);
    }
}