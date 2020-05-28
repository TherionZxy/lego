package com.zxyono.lego.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.entity.Order;
import com.zxyono.lego.entity.vo.OrderVo;
import com.zxyono.lego.entity.wechat.FruitWx;
import com.zxyono.lego.entity.wechat.OrderItemWx;
import com.zxyono.lego.entity.wechat.OrderWx;
import com.zxyono.lego.entity.wrapper.OrderItemWrapper;
import com.zxyono.lego.entity.wrapper.OrderItemsWrapper;
import com.zxyono.lego.entity.wrapper.OrderWrapper;
import com.zxyono.lego.mapper.FruitMapper;
import com.zxyono.lego.mapper.OrderItemMapper;
import com.zxyono.lego.mapper.OrderMapper;
import com.zxyono.lego.service.OrderService;
import com.zxyono.lego.util.ResultMap;
import com.zxyono.lego.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;
    @Resource
    private FruitMapper fruitMapper;

    @Override
    public ResultMap queryOrderList(Integer page, Integer limit, OrderWrapper orderWrapper) {
        IPage<Order> iPage = new Page<>(page, limit);
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(orderWrapper.getName()), "order_owner", orderWrapper.getName())
                .eq(StringUtils.isNotEmpty(orderWrapper.getPhone()), "order_phone", orderWrapper.getPhone())
                .eq(StringUtils.isNotEmpty(orderWrapper.getCode()), "code", orderWrapper.getCode())
                .eq(orderWrapper.getStatus() != null && orderWrapper.getStatus()>0 && orderWrapper.getStatus()<=3, "order_status", orderWrapper.getStatus())
                .ge(orderWrapper.getStartTime() != null, "create_time", orderWrapper.getStartTime())
                .le(orderWrapper.getEndTime() != null, "create_time", orderWrapper.getEndTime())
                .gt("order_status", 0);

        orderMapper.selectPage(iPage, queryWrapper);

        OrderVo orderVo = new OrderVo();
        orderVo.setPage(page);
        orderVo.setSize(limit);
        orderVo.setTotal(iPage.getTotal());
        orderVo.setOrderList(iPage.getRecords());

        return ResultMap.success(orderVo);
    }

    @Override
    public ResultMap queryAllOrderList(OrderWrapper orderWrapper) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(orderWrapper.getName()), "order_owner", orderWrapper.getName())
                .eq(StringUtils.isNotEmpty(orderWrapper.getPhone()), "order_phone", orderWrapper.getPhone())
                .eq(StringUtils.isNotEmpty(orderWrapper.getCode()), "code", orderWrapper.getCode())
                .eq(orderWrapper.getStatus() != null && orderWrapper.getStatus()>0 && orderWrapper.getStatus()<=3, "order_status", orderWrapper.getStatus())
                .le(orderWrapper.getStartTime() != null, "create_time", orderWrapper.getStartTime())
                .ge(orderWrapper.getEndTime() != null, "create_time", orderWrapper.getEndTime());

        return ResultMap.success(orderMapper.selectList(queryWrapper));
    }

    @Override
    public ResultMap queryOrderNums() {
        return ResultMap.success(orderMapper.getOrderNums());
    }

    @Override
    public ResultMap queryOrderById(Long orderId) {
        return ResultMap.success(orderMapper.selectById(orderId));
    }

    @Override
    public ResultMap queryOrderByUserId(Long userId) {
        List<Order> orderList = orderItemMapper.getUserAllOrder(userId);
        for(Order order: orderList) {
            List<OrderItemWx> fruits = orderItemMapper.getOrderItemByorderId(order.getOrderId());
            order.setOrderItems(fruits);
        }
        return ResultMap.success(orderList);
    }

    @Override
    public ResultMap queryOrderByUserIdWithStatus(Long userId, Integer status) {
        List<Order> orderList = orderItemMapper.getUserAllOrderWithStatus(userId, status);
        for(Order order: orderList) {
            List<OrderItemWx> fruits = orderItemMapper.getOrderItemByorderId(order.getOrderId());
            order.setOrderItems(fruits);
        }
        return ResultMap.success(orderList);
    }

    @Override
    public ResultMap createOrder(OrderItemsWrapper wrappers, Long userId) {
        // 构建待插入的订单数据
        Order order = new Order();
        order.setOrderOwner(wrappers.getName());
        order.setOrderPhone(wrappers.getPhone());
        order.setCreateTime(new Date());
        order.setUserId(userId);
        // 组装order的content
        StringBuilder sb = new StringBuilder();
        for (OrderItemWrapper wrapper: wrappers.getFruits()) {
            FruitWx fruit = fruitMapper.queryFruitWxById(wrapper.getId());
            Date now = new Date();
            if (fruit.getStart() != null && fruit.getEnd() != null && fruit.getStart().before(now) && fruit.getEnd().after(now)) {
                wrapper.setPrice(fruit.getPrice());
            } else {
                wrapper.setPrice(fruit.getOrigin());
            }
            String fruitName = fruit.getName();
            sb.append(fruitName).append(wrapper.getNumber()).append("份 ");
        }
        order.setOrderContent(sb.toString());
        orderMapper.insert(order);


        for (OrderItemWrapper wrapper: wrappers.getFruits()) {
            wrapper.setOrderId(order.getOrderId());
            orderItemMapper.insertOne(wrapper);
        }

        return ResultMap.success("订单生成成功");
    }

    @Override
    public ResultMap deleteOrderById(Long orderId) {
        return null;
    }

    @Override
    public ResultMap updateOrderStatusToComplete(Long orderId) {
        return ResultMap.success(orderMapper.changeOrderStatusComplete(orderId));
    }

    @Transactional
    @Override
    public ResultMap updateOrderStatusToPayed(Long userId, Long orderId) {
        return ResultMap.success(orderMapper.changeOrderStatusPayed(userId, orderId));
    }
}