package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Order;
import com.zxyono.lego.entity.OrderItem;
import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.entity.wechat.OrderItemWx;
import com.zxyono.lego.entity.wrapper.OrderItemWrapper;
import com.zxyono.lego.entity.wrapper.TotalOrderWrapper;
import com.zxyono.lego.mapper.provider.OrderItemSelectProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.Date;
import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @SelectProvider(type = OrderItemSelectProvider.class, method = "listByWrapperWithPage")
    public List<TotalOrder> queryListWithPage(@Param("page") Integer page, @Param("limit") Integer limit, @Param("wrapper") TotalOrderWrapper wrapper);

    @SelectProvider(type = OrderItemSelectProvider.class, method = "listByWrapper")
    public List<TotalOrder> queryList(@Param("wrapper") TotalOrderWrapper wrapper);

    @SelectProvider(type = OrderItemSelectProvider.class, method = "count")
    public Long total(@Param("wrapper") TotalOrderWrapper wrapper);

    /**
     * 查询用户所有订单
     * @param userId
     * @return
     */
    @Select("select * from tb_order where user_id=#{userId}")
    public List<Order> getUserAllOrder(@Param("userId") Long userId);


    @Select("select * from tb_order where user_id=#{userId} and order_status=#{status}")
    public List<Order> getUserAllOrderWithStatus(@Param("userId") Long userId, @Param("status") Integer status);


    /**
     * 查询一个订单的所有订单项
     * @param orderId
     * @return
     */
    @Select("select A.fruit_id,fruit_name as `fruitname`,fruit_num as `number`,fruit_price as price from " +
            "tb_order_item A left join tb_fruit B on A.fruit_id=B.fruit_id where order_id=#{orderId}")
    public List<OrderItemWx> getOrderItemByorderId(@Param("orderId") Long orderId);


    @Insert("insert into tb_order_item(order_id,fruit_id,fruit_num,fruit_price) values(#{wrapper.orderId},#{wrapper.id},#{wrapper.number},#{wrapper.price})")
    public Integer insertOne(@Param("wrapper") OrderItemWrapper wrapper);
}
