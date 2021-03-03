package com.dgut.blog.parm;

import lombok.Data;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 增加用户信息请求参数
 * @createDate: 2021/3/3
 */

@Data
public class AddUserInfoPARM {

    private String userName;
    private String password;
    private String nickname;
    private Boolean enabled;
    private String email;
    private String icon;
    List<String> roles;
}
