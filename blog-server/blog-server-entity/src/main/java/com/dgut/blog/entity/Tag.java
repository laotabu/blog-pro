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

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章标签(文章类别实体类)
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tag")
@TableName("tag")
public class Tag {
    /**
     * 文章标签Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标签名
     */
    @Column(columnDefinition = "varchar(36) null default null comment '文章标签名'")
    private String tagName;

}
