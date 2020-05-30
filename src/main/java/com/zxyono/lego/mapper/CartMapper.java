package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Cart;
import com.zxyono.lego.entity.wechat.CartWx;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select display, intro, fruit_name as `name`, discount_price as price, norm_price as origin, fruit_intro as `desc`, C.fruit_id as id, fruit_num as num \n" +
            "from tb_cart A left join `tb_user` B on A.user_id=B.user_id left join tb_fruit C on A.fruit_id=C.fruit_id where B.open_id=#{openid}")
    public List<CartWx> getCartWxList(@Param("openid") String openid);

    @Delete("delete from tb_cart where user_id = #{userId} and fruit_id = #{id}")
    public Integer deleteById(@Param("userId") Long userId, @Param("id") Long id);

    @Update("update tb_cart set fruit_num=#{num} where user_id=#{userId} and fruit_id=#{id}")
    public Integer updateById(@Param("userId") Long userId, @Param("id") Long id,@Param("num") Integer num);

    @Select("select cart_id from tb_cart where fruit_id=#{id}")
    public Long checkCartByFruitId(@Param("id") Long id);

}
