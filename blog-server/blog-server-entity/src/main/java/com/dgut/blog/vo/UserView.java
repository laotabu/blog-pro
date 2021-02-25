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
import java.time.LocalDateTime;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户阅读量实体类
 * @createDate: 2021/2/25
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_view")
@TableName("user_view")
public class UserView {

    /**
     * Id
     */
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户Id
     */
    @Column(columnDefinition = "bigint null default null comment '用户Id'")
    private Long userId;

    /**
     * 用户总阅读量
     */
    @Column(columnDefinition = "bigint default 0 comment '用户总阅读量'")
    private Long userViews;

    /**
     * 最后一次更新时间
     */
    @Column(columnDefinition = "datetime not null comment '最后一次更新时间'")
    private LocalDateTime lastUpdateTime;

}
