package com.dgut.blog.utls;

import com.dgut.blog.entity.Role;
import com.dgut.blog.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 工具类
 * @createDate: 2021/2/25
 */
@Component
public class CustomUtils {

    /***
     * 删除html标签
     * @param content
     * @return
     */
    public String stripHtml(String content) {
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * 获取内容摘要
     * @param content 文章内容
     * @param num 截取多少当摘要
     * @return
     */
   public String subStringHtmlContent(String content, Integer num) {
       return content.substring(0,
               content.length() > num ? num : content.length());
   }

    /**
     * 获取当前用户
     * @return User对象（自定义对象）
     */
    public User getCurrentUser(){
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public boolean checkIsSupperAdmin(){
        List<Role> roles = this.getCurrentUser().getRoles();
        if (!roles.isEmpty()){
            for (Role role : roles) {
                if (role.getRoleName().equals("超级管理员")){
                    return true;
                }
            }
        }
        return false;

    }
}
