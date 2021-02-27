package com.dgut.blog.security.hander;

import com.dgut.blog.entity.User;
import com.dgut.blog.utls.CustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 登录成功处理器
 * @createDate: 2021/2/27
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    CustomUtils customUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();

        String str = "{\"status\":\"success\",\"msg\":\"登录成功\",\"isSuperAdmin\":\""+
                customUtils.checkIsSupperAdmin()+
                "\"}";
        out.write(str);
        out.flush();
        out.close();
    }
}
