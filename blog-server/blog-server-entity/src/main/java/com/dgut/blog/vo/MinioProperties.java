package com.dgut.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: Minio 参数实体类
 * @createDate: 2021/3/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MinioProperties {

    /**
     * Minio 地址
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * Minio 登录用户
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * Minio 登录密码
     */
    @Value("${minio.secretKey}")
    String secretKey;

    /**
     * Minio 桶名称
     */
    @Value("${minio.bucketName}")
    String bucketName;

}
