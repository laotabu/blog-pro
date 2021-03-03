package com.dgut.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dgut.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户mapper文件
 * @createDate: 2021/2/25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getUsersByNickname(String nickname);

    User getUserByUserId(Long userId);

    boolean updateUserState(Boolean state, Long userId);

    boolean removeUserById(Long userId);

    boolean updateUserPrimaryInfoByUserId(Long id, String newEmail, String newPassword, String newNickname);

    boolean updateUserIcon(String icon, Long userId);

    boolean registerUser(String email, Boolean enabled, String icon, String nickname, String userName, String password, LocalDateTime now);
}
