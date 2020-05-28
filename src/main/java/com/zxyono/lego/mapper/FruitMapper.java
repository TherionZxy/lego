package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Fruit;
import com.zxyono.lego.entity.wechat.FruitWx;
import com.zxyono.lego.mapper.provider.FruitWxSelectProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FruitMapper extends BaseMapper<Fruit> {
    @SelectProvider(type = FruitWxSelectProvider.class, method = "list")
    public List<FruitWx> queryFruitWxList(String searchKey, Integer activity);

    @Select("select  fruit_id as `id`,fruit_name as `name`, fruit_intro as `desc`, display, intro, discount_price as " +
            "price, norm_price as origin, is_flash_sale as `status`, start_time as `start`, end_time as `end` from tb_fruit where fruit_id=#{id}")
    public FruitWx queryFruitWxById(@Param("id") Long id);

    @Update("update tb_fruit set is_flash_sale=0,discount_price=norm_price,start_time=null,end_time=null,max_num=10 " +
            "where fruit_id=#{id}")
    public Integer removeFruitFromFlashSale(@Param("id") Long id);
}
