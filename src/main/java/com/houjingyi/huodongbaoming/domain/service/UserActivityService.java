package com.houjingyi.huodongbaoming.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houjingyi.huodongbaoming.domain.entity.UserActivity;

import java.time.LocalDateTime;

/**
 * 用户活动Service
 *
 * @author yuelong.liang
 */
public interface UserActivityService extends IService<UserActivity> {

    /**
     * 根据活动和用户 id 获取用户报名活动
     *
     * @param activityId activity
     * @return UserActivity
     */
    UserActivity getByActivityIdAndUserId(Long activityId);

    /**
     * 根据用户id和今天获取有效的报名记录
     *
     * @return UserActivity
     */
    UserActivity getByActivityDate(LocalDateTime localDateTime);

}
