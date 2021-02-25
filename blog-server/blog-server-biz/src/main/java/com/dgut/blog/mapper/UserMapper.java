package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    User loadUserByUsername(@Param("username") String username);
//
//    long reg(User user);
//
//    int updateUserEmail(@Param("email") String email, @Param("id") Long id);
//
//    List<User> getUserByNickname(@Param("nickname") String nickname);
//
//    List<Role> getAllRole();
//
//    int updateUserEnabled(@Param("enabled") Boolean enabled, @Param("uid") Long uid);
//
//    int deleteUserById(Long uid);
//
//    int deleteUserRolesByUid(Long id);
//
//    int setUserRoles(@Param("rids") Long[] rids, @Param("id") Long id);
//
//    User getUserById(@Param("id") Long id);
}
