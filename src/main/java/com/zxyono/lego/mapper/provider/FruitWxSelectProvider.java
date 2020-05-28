package com.zxyono.lego.mapper.provider;

import com.zxyono.lego.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * searchKey 表示搜索关键字
 * activity 表示搜索标识：
 *      == 0 ：搜索所有已上架的水果（不含活动水果）
 *      == 1 ：查询距离活动开始不超过一个小时的水果
 *      == 2 ：查询活动已开始但没结束的水果
 */
public class FruitWxSelectProvider {

    public String list(String searchKey, Integer activity) {
        SQL querySql = new SQL();
        querySql.SELECT("fruit_id as `id`,fruit_name as `name`, fruit_intro as `desc`, display, intro, " +
                "discount_price as price, norm_price as origin, max_num as `max`,start_time as `start`,end_time as `end`");
        querySql.FROM("tb_fruit");

        String condition = "is_sale=1";

        if (StringUtils.isNotEmpty(searchKey)) {
            condition = condition +  " and fruit_name like '%" + searchKey + "%' ";
        }

        if (activity != null) {
            if (activity == 0) {
                condition = condition + " and date_add(now(), interval 1 hour) < start_time";
            }
            if (activity == 1) {
                condition = condition + " and date_add(now(), interval 1 hour) > start_time and now() < start_time";
            }
            if (activity == 2) {
                condition = condition + " and now() > start_time and now() < end_time";
            }
        }

        querySql.WHERE(condition);

        return querySql.toString();
    }
}