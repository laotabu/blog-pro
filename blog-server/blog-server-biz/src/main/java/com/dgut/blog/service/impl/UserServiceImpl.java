package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.Role;
import com.dgut.blog.entity.User;
import com.dgut.blog.mapper.UserMapper;
import com.dgut.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类service服务类实现类
 * @createDate: 2021/2/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;


    /***
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    @Override
    public User getUserByUsername(String username) {
        return this.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
    }

    /**
     * 注册用户
     * @param user 用户对象
     * @return
     */
    @Override
    public boolean registerUser(User user) {
        return this.save(user);
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
     * 根据用户Id设置用户状态
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
        return this.removeById(userId);
    }


}
