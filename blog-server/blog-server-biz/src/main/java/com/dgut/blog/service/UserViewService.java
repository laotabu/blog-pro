package com.dgut.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dgut.blog.vo.UserView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 2/25/2021 
 *@description: 用户阅读量实体类service服务类
 *
 */
public interface UserViewService extends IService<UserView> {

    void userViewsStatisticsPerDay();

    void userViewIncrement(Long articleId);

    List<LocalDateTime> getStatisticalTimesByUserId(Long userId);

    List<Long> getStatisticalDataByUserId(Long userId);

    List<UserView> getUserViewByUserId(Long userId);
}
