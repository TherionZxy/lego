package com.zxyono.lego.mapper.provider;

import com.zxyono.lego.entity.extend.TotalOrder;
import com.zxyono.lego.entity.wrapper.TotalOrderWrapper;
import com.zxyono.lego.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderItemSelectProvider {

    public SQL params(SQL sql, TotalOrderWrapper wrapper) {
        String conition = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 如果日期不为空
        if (wrapper.getTime() != null) {
            conition = "order_id in (select order_id from `tb_order` where create_time > CAST(CAST('" + format.format(wrapper.getTime()) + "' AS DATE) AS DATETIME) ";
            Date oneDayAfter = new Date(wrapper.getTime().getTime() + 86400000);
            conition += "and create_time < CAST(CAST('" + format.format(oneDayAfter) + "' AS DATE) AS DATETIME)) ";
        } else {
            Date now = new Date();
            conition = "order_id in (select order_id from `tb_order` where create_time > CAST(CAST('" + format.format(now) + "' AS DATE) AS DATETIME)) ";
        }
        // 如果fruitname不为空
        if (StringUtils.isNotEmpty(wrapper.getFruitName())) {
            conition = conition + "and fruit_name like '%" + wrapper.getFruitName() + "%' ";
        }
        sql.WHERE(conition);
        return sql;
    }

    public String listByWrapperWithPage(Integer page, Integer limit, TotalOrderWrapper wrapper) {
        SQL querySql = new SQL();

        querySql.SELECT("fruit_name as `name`, sum(fruit_num) as `number`")
                .FROM("`tb_order_item` as A")
                .LEFT_OUTER_JOIN("`tb_fruit` as B on A.fruit_id=B.fruit_id");

        params(querySql, wrapper);
        querySql.GROUP_BY("A.fruit_id");

        querySql.LIMIT(page - 1 + "," + limit);
        return querySql.toString();
    }

    public String listByWrapper(TotalOrderWrapper wrapper) {
        SQL querySql = new SQL();

        querySql.SELECT("fruit_name as `name`, sum(fruit_num) as `number`")
                .FROM("`tb_order_item` as A")
                .LEFT_OUTER_JOIN("`tb_fruit` as B on A.fruit_id=B.fruit_id");

        params(querySql, wrapper);
        querySql.GROUP_BY("A.fruit_id");

        return querySql.toString();
    }

    public String count(TotalOrderWrapper wrapper) {
        SQL querySql = new SQL();

        querySql.SELECT("count(1) as total ")
                .FROM("`tb_order_item` as A")
                .LEFT_OUTER_JOIN("`tb_fruit` as B on A.fruit_id=B.fruit_id");
        params(querySql, wrapper);
        return querySql.toString();
    }

    public static void main(String[] args) {
        OrderItemSelectProvider provider = new OrderItemSelectProvider();
        TotalOrderWrapper wrapper = new TotalOrderWrapper();
        String result = provider.count(wrapper);
        System.out.println(result);
    }
}