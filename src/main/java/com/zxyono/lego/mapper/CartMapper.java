package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Cart;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
//    @Results(id = "cartResultMap", value = {
//            @Result(property = "user", column = "cart_id", one=@One(select = "com.zxyono.lego.mapper.UserMapper.selectById",fetchType = FetchType.EAGER)),
//            @Result(property = "fruit", column = "cart_id", one=@One(select = "com.zxyono.lego.mapper.UserMapper.selectById",fetchType = FetchType.EAGER)),
//            @Result(property = "fruitNum", column = "fruit_num")
//    })
//    @Select("select * from cart where cart_id=#{cartId}")
//    public Cart getCart(int cartId);

}
