package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.vo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类与角色类关联表mapper文件
 * @createDate: 2021/2/26
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    boolean addRolesByUserId(List<Long> rolesIds, Long userId);
}
