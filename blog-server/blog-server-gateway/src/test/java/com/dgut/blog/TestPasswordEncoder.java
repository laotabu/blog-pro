package com.dgut.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 3/2/2021 
 *@description:
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class TestPasswordEncoder {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void encodePassword(){
        System.out.println(passwordEncoder.encode("123"));
    }
}
