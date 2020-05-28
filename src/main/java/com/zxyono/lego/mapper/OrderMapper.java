package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Order;
import com.zxyono.lego.entity.extend.OrderNum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select CAST(create_time AS DATE) as `date`, count(1) as `number` from `tb_order` group by `date` limit 0,10")
    public List<OrderNum> getOrderNums();

    /**
     * 修改订单状态为 2（订单已完成），只能通过order_status=1改变为order_status=2
     * @param orderId
     * @return
     */
    @Update("update tb_order set order_status=2 where order_id=#{orderId} and order_status=1")
    public Integer changeOrderStatusComplete(@Param("orderId") Long orderId);

    /**
     * 修改订单状态为 1（订单已付款），生成提货码
     * @param orderId
     * @return
     */
    @Update("update tb_order set `code`=(select new_code from (select concat(date_format(now(),'%d'),'-'," +
            "lpad(max(substring_index(`code`,'-',-1)+1),4,'0')) as `new_code` from tb_order where " +
            "CAST(create_time as DATE)=CAST(now() as DATE)) t), order_status=1 where `order_id`=#{orderId} and " +
            "order_status=0 and user_id=#{userId}")
    public Integer changeOrderStatusPayed(@Param("userId") Long userId, @Param("orderId") Long orderId);
}
