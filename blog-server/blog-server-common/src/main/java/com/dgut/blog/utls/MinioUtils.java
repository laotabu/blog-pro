package com.dgut.blog.utls;

import com.dgut.blog.vo.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: minio 7.0.1版本
 * @createDate: 2021/3/2
 */
@Component
@Data
public class MinioUtils {

    @Autowired
    private MinioProperties minioProperties;

    private MinioClient instance;

    /**
     * @PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次
     */
    @PostConstruct
    public void init() {
        System.out.println("开始初始化minio工具类参数" );
        System.out.println(minioProperties);
        try {
            instance = new MinioClient(minioProperties.getEndpoint(),
                    minioProperties.getAccessKey(),
                    minioProperties.getSecretKey());
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        }
    }
}
