package com.dgut.blog;

import com.dgut.blog.utls.MinioUtils;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import io.minio.http.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description:
 * @createDate: 2021/3/2
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class TestMinio {

    @Autowired
    MinioUtils minioUtils;

    @Test
    public void testUpload() throws IOException, NoSuchAlgorithmException, InsufficientDataException, InternalException, InvalidResponseException, InvalidKeyException, InvalidBucketNameException, XmlParserException, ErrorResponseException, InvalidExpiresRangeException {
        File file = new File("D:\\src=http___ww2.sinaimg.cn_mw690_e3ebbd1fly1gmr70uhz7jj20u00u0tj0.jpg&refer=http___www.sina.jpg");
        MinioClient instance = minioUtils.getInstance();
        InputStream inputStream = new FileInputStream(file);
        String ContentType = "Content-Type:application/x-www-form-urlencoded;charset=utf-8";
        instance.putObject("blogicon", "123", inputStream, new PutObjectOptions(inputStream.available(), -1));
        String blogicon = instance.getPresignedObjectUrl(Method.GET, "blogicon", "123", 60 * 60 * 24, null);
        System.out.println(blogicon);
        inputStream.close();
    }
}
