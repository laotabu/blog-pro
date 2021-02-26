package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.entity.Role;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户角色实体类service服务
 * @createDate: 2021/2/25
 */
public interface RoleService extends IService<Role> {

    List<Role> getRolesByUserId(Long userId);

    List<Role> getAllRole();
}
