package com.dgut.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 主启动类
 * @createDate: 2021/2/24
 */
@SpringBootApplication
@EntityScan(basePackages = "com.dgut.blog")
@MapperScan(basePackages = "com.dgut.blog.mapper")
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
