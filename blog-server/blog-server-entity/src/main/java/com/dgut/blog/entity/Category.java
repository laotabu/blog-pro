package com.dgut.blog.entity;

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
import java.time.LocalDateTime;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章栏目实体类
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@TableName("category")
public class Category {
    /**
     * 文章栏目Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章栏目名
     */
    @Column(columnDefinition = "varchar(36) null default null comment '文章栏目名'")
    private String cateName;


    /**
     * 文章栏目创建日期
     */
    @Column(columnDefinition = "datetime null default null comment '文章栏目创建日期'")
    private LocalDateTime createDate;


}
