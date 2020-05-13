package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.OrderItem;
import com.zxyono.lego.entity.extend.TotalOrder;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Select(value = "select fruit_name as `name`,sum(fruit_num) as `number`" +
            " from `order_item` as A left join `fruit` as B on A.fruit_id=B.fruit_id" +
            " where order_id in (select order_id from `order` where create_time > CAST(CAST(SYSDATE()AS DATE)AS DATETIME))" +
            " group by A.fruit_id")
    public List<TotalOrder> queryTotalOrderList();
}
