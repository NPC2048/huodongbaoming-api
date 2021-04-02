package com.houjingyi.huodongbaoming.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houjingyi.huodongbaoming.common.enums.UserActivitySignupStatusEnums;
import com.houjingyi.huodongbaoming.domain.entity.UserActivity;
import com.houjingyi.huodongbaoming.domain.mapper.UserActivityMapper;
import com.houjingyi.huodongbaoming.domain.service.UserActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户活动 Service 实现
 *
 * @author yuelong.liang
 */
@Service
public class UserActivityServiceImpl extends ServiceImpl<UserActivityMapper, UserActivity> implements UserActivityService {
    @Override
    public UserActivity getByActivityIdAndUserId(Long activityId) {
        return this.baseMapper.selectOne(Wrappers.<UserActivity>lambdaQuery()
                .eq(UserActivity::getUserId, StpUtil.getLoginId())
                .eq(UserActivity::getActivityId, activityId));
    }

    @Override
    public UserActivity getByActivityDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return this.lambdaQuery().eq(UserActivity::getUserId, StpUtil.getLoginId())
                .in(UserActivity::getStatus, UserActivitySignupStatusEnums.ACTIVE.getCode()
                        , UserActivitySignupStatusEnums.SIGNED_IN.getCode())
                // 日期小于等于明天，即今天
                .ge(UserActivity::getActivityDate, localDate)
                .le(UserActivity::getActivityDate, localDate.plusDays(1))
                .one();
    }
}
