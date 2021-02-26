package com.dgut.blog.controller;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.service.UserService;
import com.dgut.blog.utls.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户管理controller
 * @createDate: 2021/2/25
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUtils customUtils;

    /***
     * 获取当前用户名字
     * @return
     */
    @RequestMapping("/currentUserName")
    public String currentUserName() {
        return customUtils.getCurrentUser().getNickname();
    }

    /**
     * 获取当前用户Id
     * @return
     */
    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return customUtils.getCurrentUser().getId();
    }

    /**
     * 获取当前用户邮箱
     * @return
     */
    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return customUtils.getCurrentUser().getEmail();
    }

    /**
     * 确认是否为超级管理员
     * @return
     */
    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = customUtils.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新用户邮箱
     * @param email
     * @return
     */
    @RequestMapping(value = "/updateUserEmail",method = RequestMethod.PUT)
    public ResponseDTO updateUserEmail(String email) {
        if (userService.updateUserEmailByUserId(email,
                customUtils.getCurrentUser().getId())) {
            return new ResponseDTO("success", "更新成功!");
        }
        return new ResponseDTO("error", "更新失败!");
    }
}
