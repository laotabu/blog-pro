//package com.dgut.blog.common;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import lombok.Data;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import java.time.LocalDateTime;
//
///**
// * @author: lishengdian | 932978775@qq.com
// * @version: 1.0.0
// * @description: 基础实体类
// * @createDate: 2021/2/25
// */
//@Entity
//@Data
//public class BaseEntity {
//
//    @Id
//    @TableId(type = IdType.ASSIGN_UUID)
//    @Column(columnDefinition = "varchar(36) comment 'id字段'")
//    String id;
//
//    @CreatedDate
//    @Column(columnDefinition = "datetime comment '创建时间'")
//    LocalDateTime createTime;
//
//    @LastModifiedDate
//    @Column(columnDefinition = "datetime comment '最后一次更新时间'")
//    LocalDateTime upateTime;
//
//    @CreatedBy
//    @Column(columnDefinition = "varchar(36) comment '创建者'")
//    String createBy;
//
//    @LastModifiedBy
//    @Column(columnDefinition = "varchar(36) comment '最后一次更新者'")
//    String upateBy;
//
//}
