package com.dgut.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: minio 参数实体类
 * @createDate: 2021/3/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MinioProperties {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    String secretKey;

    @Value("${minio.bucketName}")
    String bucketName;


}
