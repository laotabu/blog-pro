package com.dgut.blog.parm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 更新用户信息请求参数
 * @createDate: 2021/3/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoPARM {
    String inputNickName;
    String newPassword;
    String oldPassword;
    String inputEmail;
}
