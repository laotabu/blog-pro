package com.dgut.blog.controller;


import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.User;
import com.dgut.blog.service.UserService;
import com.dgut.blog.utls.CustomUtils;
import com.dgut.blog.utls.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 登录注册controller
 * @createDate: 2021/2/25
 */
@RestController
public class LoginRegController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUtils customUtils;
    /**
     * 登录失败
     * @return
     */
    @RequestMapping("/login_error")
    public ResponseDTO loginError() {
        return new ResponseDTO("error", "登录失败!");
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping("/login_success")
    public ResponseDTO loginSuccess() {
        System.out.println("当前用户ID为： " + customUtils.getCurrentUser().getId());
        return new ResponseDTO("success", "登录成功!");
    }

    /**
     * 尚未登录
     * @return
     */
    @RequestMapping("/login_page")
    public ResponseDTO loginPage() {
        return new ResponseDTO("error", "尚未登录，请登录!");
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/reg")
    public ResponseDTO reg(User user) {
        int result = userService.registerUser(user);
        if (result == 0) {
            //成功
            return new ResponseDTO("success", "注册成功!");
        } else if (result == 1) {
            return new ResponseDTO("error", "用户名重复，注册失败!");
        } else {
            //失败
            return new ResponseDTO("error", "注册失败!");
        }
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.removeAttribute("verify_code");
        session.setAttribute("verify_code", text);
        System.out.println("生成的验证码为： " + session.getAttribute("verify_code"));
        VerificationCode.output(image,response.getOutputStream());
    }
}
