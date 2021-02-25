package com.dgut.blog.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 角色与用户连接表
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_role")
@TableName("user_role")
public class UserRole {

    /**
     * Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户角色Id
     */
    @Column(columnDefinition = "bigint null default null comment '用户角色Id'")
    private Long roleId;

    /**
     * 用户Id
     */
    @Column(columnDefinition = "bigint null default null comment '用户Id'")
    private Long userId;
}
