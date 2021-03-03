package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Role;
import com.dgut.blog.mapper.RoleMapper;
import com.dgut.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户角色类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    /***
     * 根据userId获取用户角色列表
     * @param userId 用户Id
     * @return
     */
    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    /**
     * 获取所有角色列表
     * @return
     */
    @Override
    public List<Role> getAllRole() {
        return this.lambdaQuery().list();
    }

    /***
     * 根据角色名获取角色Id
     * @param roleNames
     * @return
     */
    @Override
    public List<Long> getRoleIdsByRoleName(List<String> roleNames) {
       List<Long> ids = new ArrayList<>();
        List<Role> roles = this.lambdaQuery()
                .in(Role::getRoleName, roleNames)
                .list();
        if (!roles.isEmpty()){
            ids = roles
                    .stream()
                    .map(Role::getId)
                    .collect(Collectors.toList());
        }
        return ids;
    }

    @Override
    public boolean deleteRoleByIds(List<Long> idList) {
        return this.removeByIds(idList);
    }

    @Override
    public boolean addRole(Role role) {
        return this.save(role);
    }

    @Override
    public boolean updateRoleById(Role role) {
        return this.updateById(role);
    }

    /***
     * 根据角色名获取角色对象
     * @param roleName
     * @return
     */
    @Override
    public Role getRoleByRoleName(String roleName) {
        return this.lambdaQuery().eq(Role::getRoleName, roleName).one();
    }


}
