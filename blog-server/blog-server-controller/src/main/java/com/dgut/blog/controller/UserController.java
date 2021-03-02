package com.dgut.blog.controller;

import com.dgut.blog.dto.ResponseDTO;
import com.dgut.blog.entity.User;
import com.dgut.blog.parm.UpdateUserInfoPARM;
import com.dgut.blog.service.UserService;
import com.dgut.blog.utls.CustomUtils;
import com.dgut.blog.utls.MinioUtils;
import com.dgut.blog.vo.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import io.minio.http.Method;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description: 用户管理controller
 * @createDate: 2021/2/25
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUtils customUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MinioUtils minioUtils;

    @Autowired
    MinioProperties minioProperties;

    /***
     * 获取当前用户名字
     * @return
     */
    @RequestMapping("/currentUserName")
    public String currentUserName() {
        return customUtils.getCurrentUser().getNickname();
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    @RequestMapping("/currentUserId")
    public Long currentUserId() {
        return customUtils.getCurrentUser().getId();
    }

    /**
     * 获取当前用户邮箱
     *
     * @return
     */
    @RequestMapping("/currentUserEmail")
    public String currentUserEmail() {
        return customUtils.getCurrentUser().getEmail();
    }

    /**
     * 确认是否为超级管理员
     *
     * @return
     */
    @RequestMapping("/isAdmin")
    public Boolean isAdmin() {
        List<GrantedAuthority> authorities = customUtils.getCurrentUser().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().contains("超级管理员")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 更新用户邮箱
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/updateUserEmail", method = RequestMethod.PUT)
    public ResponseDTO updateUserEmail(String email) {
        if (userService.updateUserEmailByUserId(email,
                customUtils.getCurrentUser().getId())) {
            return new ResponseDTO("success", "更新成功!");
        }
        return new ResponseDTO("error", "更新失败!");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping(value = "/userInfo")
    public User userInfo() {
        System.out.println("获取用户信息");
        return userService.getUserByUserId(customUtils.getCurrentUser().getId());
    }

    /**
     * 更新用户基础信息（邮箱，别称，密码）
     * @param parm
     * @return
     */
    @PostMapping(value = "/updateUserInfo")
    public ResponseDTO updateUserInfo(UpdateUserInfoPARM parm)
    {
        String oldPassword = parm.getOldPassword();
        User currentUser = customUtils.getCurrentUser();
        if(!passwordEncoder.matches(oldPassword,
                currentUser.getPassword())){
            return new ResponseDTO("error","原密码错误");
        }
        System.out.println("用户存在，用户名为：" + currentUser.getUsername());
        String inputPassword = parm.getNewPassword();
        String encodePassword = "";
        if(inputPassword!=null && inputPassword!=""){
            System.out.println("密码不为空，加密");
            encodePassword = passwordEncoder.encode(inputPassword);
        }
        boolean result = userService.updateUserPrimaryInfoByUserId(currentUser.getId(),
                parm.getInputEmail(),
                encodePassword,
                parm.getInputNickName());
        System.out.println(result);
        if (result) {
            return new ResponseDTO("success","更新成功");
        }else {
            return new ResponseDTO("fail","更新失败，请检查网络是否正常");
        }
    }


    /**
     * 上传头像
     * @param req
     * @param image
     * @return
     */
    @PostMapping(value = "userIconUpload")
    public ResponseDTO uploadUserIcon(HttpServletRequest req, MultipartFile image) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String imgName = UUID.randomUUID() + "_" + sdf.format(new Date()) + "_" + image.getOriginalFilename().replaceAll(" ", "");
        MinioClient instance = minioUtils.getInstance();
        File file = new File(imgName);
        IOUtils.write(image.getBytes(), new FileOutputStream(file));
        InputStream inputStream = new FileInputStream(file);
        try {
            instance.putObject(minioProperties.getBucketName(),
                                imgName,
                                inputStream,
                    new PutObjectOptions(inputStream.available(), -1));
            String presignedObjectUrl = instance.getPresignedObjectUrl(Method.GET,
                    minioProperties.getBucketName(),
                    imgName,
                    60 * 60 * 24 * 7,
                    null);
            if (presignedObjectUrl != "" && presignedObjectUrl != null) {
                if(userService.updateUserIcon(presignedObjectUrl,
                        customUtils.getCurrentUser().getId())){
                    return new ResponseDTO("success", presignedObjectUrl);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
            if (file.isFile() && file.exists()) {
                System.out.println("删除文件");
                file.delete();
            }
        }
        return new ResponseDTO("error", "上传失败，请检查网络是否正常");

    }


}
