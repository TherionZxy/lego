package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
}
