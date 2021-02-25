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
 * @description: 文章实体类与文章类别连接表
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article_tag")
@TableName("article_tag")
public class ArticleTag {
    /**
     * Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文章标签Id
     */
    @Column(columnDefinition = "bigint null default null comment '文章标签Id'")
    private Long tagId;

    /**
     * 文章Id
     */
    @Column(columnDefinition = "bigint null default null comment '文章Id'")
    private Long articleId;
}
