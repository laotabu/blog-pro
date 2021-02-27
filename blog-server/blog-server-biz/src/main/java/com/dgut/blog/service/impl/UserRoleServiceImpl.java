package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.mapper.UserRoleMapper;
import com.dgut.blog.service.UserRoleService;
import com.dgut.blog.vo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类与角色类关联表Service服务实现类
 * @createDate: 2021/2/26
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;
    /***
     * 根据用户userId增加用户角色
     * @param rolesIds 角色列表
     * @param userId 用户Id
     * @return
     */
    @Override
    public boolean addRolesByUserId(List<Long> rolesIds, Long userId) {
        return userRoleMapper.addRolesByUserId(rolesIds, userId);
    }

    /***
     * 根据用户Id删除用户与角色表关联信息
     * @param userId 用户Id
     * @return
     */
    @Override
    public boolean deleteUserRolesByUserid(Long userId) {
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("user_id", userId);
        return this.removeByMap(maps);
    }
}
