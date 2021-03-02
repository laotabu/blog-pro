package com.dgut.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 测试java文件路径
 * @createDate: 2021/3/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class TestFilePath {

    @Test
    public void getRealPath(){
        System.out.println(this.getClass().getClassLoader().getResource("").getPath());
        System.out.println(this.getClass().getClassLoader().getResource("../classes/image/").getPath());
    }
}
