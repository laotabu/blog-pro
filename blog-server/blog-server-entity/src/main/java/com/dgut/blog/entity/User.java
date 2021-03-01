package com.dgut.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户信息实体类
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@TableName("user")
public class User implements UserDetails {

    /**
     * 用户Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Column(unique = true,
            columnDefinition = "varchar(36) not null comment '用户名'")
    private String userName;

    /**
     * 用户密码
     */
    @Column(columnDefinition = "varchar(255) not null comment '用户密码'")
    private String password;

    /**
     * 用户别名
     */
    @Column(columnDefinition = "varchar(36) null default null comment '用户别名'")
    private String nickname;

    /**
     * 用户状态
     * 防止发生冲突,取消get方法
     */
    @Getter(value = AccessLevel.NONE)
    @Column(columnDefinition = "tinyint default 1 comment '1表示用户可用,0表示用户不可用'")
    private boolean enabled;

    /**
     * 用户邮箱
     */
    @Column(columnDefinition = "varchar(36) null default null comment '用户邮箱'")
    private String email;

    /**
     * 用户头像
     */
    @Column(columnDefinition = "varchar(255) null default null comment '用户头像'")
    private String icon;

    /**
     * 用户注册日期
     */
    @Column(columnDefinition = "datetime not null comment '用户注册日期'")
    private LocalDateTime registerDate;

//    @ManyToMany

    /**
     * 用户角色列表
     */
    @Transient
    @TableField(exist = false)
    List<Role> roles;


    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @JsonIgnore
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * 自定义返回user对象状态
     * @return
     */
    public boolean customcCheckUserIsEnable(){
        return this.enabled;
    }
}
