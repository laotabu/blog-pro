package com.dgut.blog.security.filter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.dgut.blog.dto.ResponseDTO;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 验证码过滤器
 * @createDate: 2021/2/28
 */
@Component
public class VerifyCodeFilter extends OncePerRequestFilter {


    private String defaultFilterProcessUrl = "/login";
    private String method = "POST";

    /***
     * 检验验证码是否正确
     * @param response
     * @param code 用户输入的验证码
     * @param verify_code 后台生成的验证码
     */
    private boolean checkCode(HttpServletResponse response, String code, String verify_code) {
        if (StrUtil.isEmpty(code)){
            throw new AuthenticationServiceException("验证码不能为空!");
        }
        if (StrUtil.isEmpty(verify_code)){
            throw new AuthenticationServiceException("验证码已失效!");
        }
        if (!StrUtil.equalsIgnoreCase(verify_code,code)){
            throw new AuthenticationServiceException("验证码错误!");
        }
        return true;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (method.equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())) {
            // 获取生成的验证码
            String verify_code = (String) request.getSession().getAttribute("verify_code");
            String code = request.getParameter("code");
            response.setContentType("application/json;charset=UTF-8");
            System.out.println("开始验证验证码(系统生成的验证码为):"+verify_code);
            System.out.println("开始验证验证码(用户输入的验证码为):"+code);

            if (StrUtil.isEmpty(code)){
                response.getWriter().write(JSON.toJSONString(new ResponseDTO("error","验证码不能为空")));
                request.getSession().removeAttribute("verify_code");
                return;
            }
            if (StrUtil.isEmpty(code)){
                response.getWriter().write(JSON.toJSONString(new ResponseDTO("error","验证码失效")));
                return;
            }
            if (!StrUtil.equalsIgnoreCase(verify_code,code)){
                request.getSession().removeAttribute("verify_code");
                response.getWriter().write(JSON.toJSONString(new ResponseDTO("error","验证码不匹配")));
                return;
            }
        }
        chain.doFilter(request, response);

    }
}
