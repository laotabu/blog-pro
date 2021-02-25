package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Role;
import com.dgut.blog.mapper.RoleMapper;
import com.dgut.blog.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户角色类service服务实现类
 * @createDate: 2021/2/25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
