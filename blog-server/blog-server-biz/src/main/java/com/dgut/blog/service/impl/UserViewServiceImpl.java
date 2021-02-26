package com.dgut.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dgut.blog.entity.User;
import com.dgut.blog.mapper.UserMapper;
import com.dgut.blog.mapper.UserViewMapper;
import com.dgut.blog.service.UserService;
import com.dgut.blog.service.UserViewService;
import com.dgut.blog.vo.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: lishengdian | 932978775@qq.com
 * @version: 1.0.0
 * @description:
 * @createDate: 2021/2/25
 */
@Service
public class UserViewServiceImpl extends ServiceImpl<UserViewMapper, UserView> implements UserViewService {

    @Autowired
    UserViewMapper userViewMapper;
    /***
     * 每天更新用户阅读总量(还没改好)
     */
    @Override
    public void userViewsStatisticsPerDay() {

        userViewMapper.userViewsStatisticsPerDay();
    }

    @Override
    public void userViewIncrement(Long articleId) {

    }

    /***
     * 根据用户Id获取用户浏览量成长记录时间(最近七条)
     * @param userId
     * @return
     */
    @Override
    public List<LocalDateTime> getStatisticalTimesByUserId(Long userId) {
        List<LocalDateTime> localDateTimeArrayList = new ArrayList<>();
        List<UserView> userViews = this.getUserViewByUserId(userId);
        if (!userViews.isEmpty()){
            localDateTimeArrayList = userViews.stream().map(UserView::getLastUpdateTime).collect(Collectors.toList());
        }
        return localDateTimeArrayList;
    }

    /**
     * 根据用户Id获取用户浏览量(最近七条)
     * @param userId
     * @return
     */
    @Override
    public List<Long> getStatisticalDataByUserId(Long userId) {
        List<Long> integers = new ArrayList<>();
        List<UserView> userViews = this.getUserViewByUserId(userId);
        if (!userViews.isEmpty()){
            integers = userViews.stream().map(UserView::getUserViews).collect(Collectors.toList());
        }
        return integers;
    }

    /***
     * 根据用户Id获取最近七条用户浏览量成长记录
     * @param userId
     * @return
     */
    @Override
    public List<UserView> getUserViewByUserId(Long userId) {
        return this.lambdaQuery()
                .eq(UserView::getUserId, userId)
                .orderByDesc(UserView::getLastUpdateTime)
                .last("limit 7")
                .list();
    }


}
