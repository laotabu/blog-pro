package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.entity.Role;
import com.dgut.blog.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户实体类service服务类
 * @createDate: 2021/2/25
 */
public interface UserService extends IService<User> {



    int registerUser(User user);

    boolean updateUserEmailByUserId(String email, Long userId);

    List<User> getUsersByNickname(String nickname);

    User getUserByUserId(Long userId);

    boolean updateUserState(Boolean state, Long userId);

    boolean deleteUserByUserId(Long uid);

    boolean updateUserRoles(List<Long> rids, Long id);

    boolean updateUserPrimaryInfoByUserId(Long id, String email, String newPassword, String newNickname);
}
