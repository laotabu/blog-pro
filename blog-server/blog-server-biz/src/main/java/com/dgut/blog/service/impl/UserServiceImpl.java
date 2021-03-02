package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.User;
import com.dgut.blog.mapper.UserMapper;
import com.dgut.blog.service.RoleService;
import com.dgut.blog.service.UserRoleService;
import com.dgut.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类service服务类实现类
 * @createDate: 2021/2/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
     * 注册用户
     * @param user 用户对象
     * @return
     * 0 - 成功
     * 1 - 用户名重复
     */
    @Override
    public int registerUser(User user) {
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        if (!this.save(user)) {
            // 用户名重复
            return 1;
        }
        // 默认配置普通用户
        List<Long> rolesIds = new ArrayList<>();
        rolesIds.add(2L);
        boolean result = userRoleService.addRolesByUserId(rolesIds, user.getId());
        if (result) {
            // 插入成功
            return 0;
        }else {
            // 插入失败
            return 1;
        }
    }

    /**
     * 根据用户id更新用户邮箱
     * @param email 用户邮箱
     * @param userId 用户Id
     * @return
     */
    @Override
    public boolean updateUserEmailByUserId(String email, Long userId) {
        return this.lambdaUpdate()
                .eq(User::getId, userId)
                .set(User::getEmail, email)
                .update();
    }

    /**
     * 根据用户别名获取用户列表(模糊查询)
     * @param nickname 用户别名
     * @return
     */
    @Override
    public List<User> getUsersByNickname(String nickname) {
        return userMapper.getUsersByNickname(nickname);
    }

    /**
     * 根据用户Id获取用户
     * @param userId
     * @return
     */
    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.getUserByUserId(userId);
    }

    /**
     * 根据用户Id删除用户
     * @param state 用户状态
     * @param userId 用户Id
     * @return
     */
    @Override
    public boolean updateUserState(Boolean state, Long userId) {
        return userMapper.updateUserState(state, userId);
    }

    @Override
    public boolean deleteUserByUserId(Long userId) {
        return userMapper.removeUserById(userId);
    }


    /**
     * 更新用户角色
     * @param rids
     * @param id
     * @return
     */
    @Override
    public boolean updateUserRoles(List<Long> rids, Long id) {
        userRoleService.deleteUserRolesByUserid(id);
        return userRoleService.addRolesByUserId(rids, id);
    }

    /***
     * 根据用户id更新用户信息（可选择性更新，也可更新密码）
     * @param id 用户Id
     * @param newEmail 用户新邮箱
     * @param newPassword 用户新密码
     * @param newNickname 用户新别称
     * @return
     */
    @Override
    public boolean updateUserPrimaryInfoByUserId(Long id, String newEmail, String newPassword, String newNickname) {
        System.out.println("要更新的内容为：");
        System.out.println("newEmail：" + newEmail);
        System.out.println("newPassword：" + newPassword);
        System.out.println("newNickname：" + newNickname);
        return userMapper.updateUserPrimaryInfoByUserId(id, newEmail, newPassword, newNickname);
    }


    /***
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("认证逻辑开始，用户名：" + username);
        User user = this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        if (user == null) {
            user = new User();
        }
        user.setRoles(roleService
                .getRolesByUserId(user.getId()));
        System.out.println("查询到的用户为： " + user);
        return user;
    }
}
