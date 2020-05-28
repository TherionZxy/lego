package com.zxyono.lego.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxyono.lego.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from tb_role A left join tb_admin B on A.admin_id=B.admin_id where admin_name=#{name}")
    public List<Role> findRoleByAdminName(@Param("name") String name);

    @Delete("delete from tb_role where admin_id = #{adminId}")
    public int deleteRoleByAdminId(@Param("adminId") Long adminId);
}
