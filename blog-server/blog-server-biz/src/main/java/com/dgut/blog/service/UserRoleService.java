package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.vo.UserRole;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类与角色类关联表Service服务
 * @createDate: 2021/2/26
 */
public interface UserRoleService extends IService<UserRole> {

    boolean addRolesByUserId(List<Long> rolesIds, Long userId);

    boolean deleteUserRolesByUserid(Long userId);

}
