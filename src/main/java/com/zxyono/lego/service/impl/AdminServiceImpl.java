package com.zxyono.lego.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxyono.lego.entity.Admin;
import com.zxyono.lego.entity.Role;
import com.zxyono.lego.entity.vo.AdminVo;
import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.LoginException;
import com.zxyono.lego.exception.ParamException;
import com.zxyono.lego.mapper.AdminMapper;
import com.zxyono.lego.mapper.RoleMapper;
import com.zxyono.lego.service.AdminService;
import com.zxyono.lego.util.JwtTokenUtil;
import com.zxyono.lego.util.RedisUtil;
import com.zxyono.lego.util.ResultMap;
import com.zxyono.lego.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RedisUtil redisUtil;

    @Value("${lego.token.expirationMilliSeconds}")
    private long expirationMilliSeconds;


    @Override
    public ResultMap adminLogin(String username, String password) {
        // 首先查询是否存在用户
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("admin_name", username));

        // 若用户不存在，抛出异常
        if (admin == null) {
            throw new LoginException(ExceptionEnum.USER_NOT_FOUND);
        }

        // 比对密码
        if (!admin.getAdminPwd().equals(password)) {
            throw new LoginException(ExceptionEnum.PASSWORD_WRONG);
        }

        // 否则查询数据库中的权限
        List<Role> roles = roleMapper.findRoleByAdminName(admin.getAdminName());
        Set authoritiesSet = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        admin.setAuthorities(authoritiesSet);

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("adminId",admin.getAdminId().toString());
        hashMap.put("adminName", admin.getAdminName());
        hashMap.put("authorities",authoritiesSet);

        String token = JwtTokenUtil.generateToken(admin, expirationMilliSeconds);
        redisUtil.hset(token, hashMap);

        hashMap.put("token", token);
        return ResultMap.success("管理员登录成功", hashMap);
    }

    @Override
    public ResultMap adminInfo(Long adminId) {
        Admin admin = adminMapper.selectById(adminId);
        admin.setAdminPwd("");

        // 获取权限列表
        List<Role> roles = roleMapper.findRoleByAdminName(admin.getAdminName());
        Set authoritiesSet = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());
        admin.setAuthorities(authoritiesSet);

        return ResultMap.success(admin);
    }

    @Override
    public ResultMap getAdminListWithPage(Integer page, Integer limit, String username, String phone, String sort) {
        AdminVo adminVo = new AdminVo();
        // 分页细节
        IPage<Admin> iPage = new Page<>(page ,limit);

        // 封装查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(username), "admin_name" , username)
                .eq(StringUtils.isNotEmpty(phone), "admin_phone", phone)
                .orderBy(StringUtils.isNotEmpty(sort), sort.equals("+id"), "admin_id");
        adminMapper.selectPage(iPage, queryWrapper);

        adminVo.setPage(page);
        adminVo.setSize(limit);
        adminVo.setTotal(iPage.getTotal());
        adminVo.setAdminList(iPage.getRecords());
        return ResultMap.success(adminVo);
    }

    @Override
    public ResultMap modifyAdminInfo(Admin admin) {
        if (admin.getAdminId() == null) {
            throw new ParamException(ExceptionEnum.NOT_HAVE_ENTITY_ID);
        }

        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .set(StringUtils.isNotEmpty(admin.getAdminPhone()), "admin_phone", admin.getAdminPhone())
                .set(StringUtils.isNotEmpty(admin.getAdminPwd()), "admin_pwd", admin.getAdminPwd())
                .eq("admin_id", admin.getAdminId());



        return ResultMap.success(adminMapper.update(null, updateWrapper));
    }

    @Override
    @Transactional
    public ResultMap createAdmin(Admin admin) {
        // 抹掉Admin的id（防止破坏数据库自动生成Id）
        admin.setAdminId(null);
        adminMapper.insert(admin);
        // 超级管理员只有一个，创建的其他管理员权限都是普通管理员
        Role role = new Role();
        role.setAdminId(admin.getAdminId());
        role.setRoleName("ADMIN");
        roleMapper.insert(role);

        // 获取插入成功后的Id
        return ResultMap.success(admin.getAdminId());
    }

    @Override
    @Transactional
    public ResultMap removeAdminById(Long adminId) {
        // 先删除权限
        roleMapper.deleteRoleByAdminId(adminId);
        // 再删除用户
        return ResultMap.success(adminMapper.deleteById(adminId));
    }

    @Override
    public ResultMap getAdminList(String username, String phone, String sort) {
        // 封装查询条件
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like(StringUtils.isNotEmpty(username), "admin_name" , username)
                .eq(StringUtils.isNotEmpty(phone), "admin_phone", phone)
                .orderBy(StringUtils.isNotEmpty(sort), sort.equals("+id"), "admin_id");

        return ResultMap.success(adminMapper.selectList(queryWrapper));
    }
}