package com.dgut.blog;

import com.dgut.blog.controller.UserController;
import com.dgut.blog.service.UserService;
import com.dgut.blog.utls.CustomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 3/2/2021 
 *@description: 对UserController单独测试
 *
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BlogApplication.class)
@WebMvcTest(UserController.class)
public class TestJavaPath {

    @Autowired
    private MockMvc mockMvc;

    //模拟出一个userService
    @MockBean
    private UserService userService;

    @MockBean
    private CustomUtils customUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    public void testGetRealPath()
    {

    }
}
