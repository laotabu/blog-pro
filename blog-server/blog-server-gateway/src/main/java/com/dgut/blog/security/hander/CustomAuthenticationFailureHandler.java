package com.dgut.blog.security.hander;

import com.alibaba.fastjson.JSON;
import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.User;
import com.dgut.blog.utls.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 登录失败处理器
 * @createDate: 2021/2/27
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    CustomUtils customUtils;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(new ResponseDTO("error","密码错误或账户被锁定，请联系管理员")));
        out.flush();
        out.close();
    }
}
