package com.zxyono.lego.service.impl;

import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.entity.vo.TotalOrderVo;
import com.zxyono.lego.entity.wrapper.TotalOrderWrapper;
import com.zxyono.lego.mapper.OrderItemMapper;
import com.zxyono.lego.service.OrderItemService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    public ResultMap getTotalOrderListWithPage(Integer page, Integer limit, TotalOrderWrapper wrapper) {
        Long total = orderItemMapper.total(wrapper);
        List<TotalOrder> totalOrderList = orderItemMapper.queryListWithPage(page, limit, wrapper);

        TotalOrderVo totalOrderVo = new TotalOrderVo();
        totalOrderVo.setPage(page);
        totalOrderVo.setSize(limit);
        totalOrderVo.setTotal(total);
        totalOrderVo.setTotalList(totalOrderList);
        return ResultMap.success(totalOrderVo);
    }

    @Override
    public ResultMap getTotalOrderList(TotalOrderWrapper wrapper) {
        List<TotalOrder> totalList = orderItemMapper.queryList(wrapper);
        return ResultMap.success(totalList);
    }
}