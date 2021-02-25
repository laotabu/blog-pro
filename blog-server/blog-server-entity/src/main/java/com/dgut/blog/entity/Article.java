package com.dgut.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 文章实体类
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
@TableName("article")
public class Article {
    /**
     * 文章Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
    @Column(columnDefinition = "varchar(36) null default null comment '文章标题'")
    private String title;

    /**
     * md文件源码
     */
    @Column(columnDefinition = "text null default null comment 'md文件源码'")
    private String mdContent;

    /**
     * html文件源码
     */
    @Column(columnDefinition = "text null default null comment 'html文件源码'")
    private String htmlContent;

    /**
     * 文章摘要
     */
    @Column(columnDefinition = "text null default null comment '文章摘要'")
    private String summary;

    /**
     * 发布日期
     */
    @Column(columnDefinition = "datetime null default null comment '发布日期'")
    private LocalDateTime publishDate;

    /**
     * 编辑日期
     */
    @Column(columnDefinition = "datetime null default null comment '编辑日期'")
    private LocalDateTime editTime;

    /**
     * 文章状态
     */
    @Column(columnDefinition = "int null default null comment '0表示草稿箱,1表示已发表,2表示已删除'")
    private Integer state;

    /**
     * 文章阅读量
     */
    @Column(columnDefinition = "bigint default 0 comment '文章阅读量'")
    private Long pageView;

    /**
     * 文章类别Id
     */
    @Column(columnDefinition = "bigint null default null comment '文章类别Id'")
    private Long categoryId;

    /**
     * 文章所属用户Id
     */
    @Column(columnDefinition = "bigint null default null comment '文章所属用户Id'")
    private Long userId;

    /**
     * 文章所属类型列表（不对该字段持久化）
     */
    @Transient
    private List<Tag> tags;

    /**
     * 文章临时标签（类别）（不对该字段持久化）
     */
    @Transient
    private String[] dynamicTags;
}
