package com.houjingyi.huodongbaoming.domain.manage.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.houjingyi.huodongbaoming.common.constant.ErrorMessageConstants;
import com.houjingyi.huodongbaoming.common.enums.ActivityStatusEnums;
import com.houjingyi.huodongbaoming.common.enums.UserActivitySignupStatusEnums;
import com.houjingyi.huodongbaoming.common.exception.BusinessException;
import com.houjingyi.huodongbaoming.common.exception.NoLogException;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.entity.UserActivity;
import com.houjingyi.huodongbaoming.domain.manage.ActivityManage;
import com.houjingyi.huodongbaoming.domain.service.ActivityService;
import com.houjingyi.huodongbaoming.domain.service.UserActivityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 活动 Manage
 *
 * @author yuelong.liang
 */
@Service
@AllArgsConstructor
public class ActivityManageImpl implements ActivityManage {


    private final UserActivityService userActivityService;

    private final ActivityService activityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void signup(Long id) {
        Activity entity = this.activityService.getById(id);
        if (entity == null) {
            throw new NoLogException(ErrorMessageConstants.NOT_FOUND_ACTIVITY);
        }
        // 判断活动状态
        if (!ActivityStatusEnums.PASS.getCode().equals(entity.getStatus())) {
            throw new NoLogException("活动不为通过状态，不允许报名");
        }
        // 是否超过数量限制
        if (entity.getLimitNum() <= entity.getNum()) {
            throw new NoLogException("报名人数已满, 不允许报名");
        }
        // 用户 id
        Long userId = StpUtil.getLoginIdAsLong();
        // 是否为自己发布的
        if (entity.getCreateUser().equals(userId)) {
            throw new NoLogException("不能报名自己发布的活动");
        }
        // 是否已报名
        UserActivity userActivity = userActivityService.getByActivityIdAndUserId(id);
        if (userActivity != null) {
            throw new NoLogException("已经报名该活动，不能重复报名");
        }
        // 今天是否已报名活动
        userActivity = userActivityService.getByActivityDate(entity.getDate());
        if (userActivity != null) {
            throw new NoLogException(entity.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "已有报名活动，不能同时报名多个活动");
        }
        // 组装实体类
        userActivity = UserActivity.builder()
                .status(UserActivitySignupStatusEnums.ACTIVE.getCode())
                .signupDate(LocalDateTime.now()).activityId(id).userId(userId).activityDate(entity.getDate()).build();
        // 报名数量 + 1
        entity.setNum(entity.getNum() + 1);
        // 保存更新
        userActivityService.save(userActivity);
        activityService.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void cancel(Long id) {
        Activity entity = this.activityService.getById(id);
        if (entity == null) {
            throw new BusinessException(ErrorMessageConstants.NOT_FOUND_ACTIVITY);
        }
        // 判断活动状态
        if (!entity.getStatus().equals(ActivityStatusEnums.PASS.getCode())) {
            throw new BusinessException("活动不为通过状态，不允许报名");
        }
        // 取消报名活动
        UserActivity userActivity = userActivityService.getByActivityIdAndUserId(id);
        // 设置为取消状态
        userActivity.setStatus(UserActivitySignupStatusEnums.CANCEL.getCode());
        // 活动人数减 1
        entity.setNum(entity.getNum() - 1);
        // 保存更新
        userActivityService.updateById(userActivity);
        activityService.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void signIn(Long id) {
        Activity activity = this.activityService.getById(id);
        if (activity == null) {
            throw new BusinessException(ErrorMessageConstants.NOT_FOUND_ACTIVITY);
        }
        // 判断活动状态
        if (!activity.getStatus().equals(ActivityStatusEnums.PASS.getCode())) {
            throw new BusinessException("活动不为通过状态，不允许签到");
        }
        UserActivity userActivity = userActivityService.getByActivityIdAndUserId(id);
        // 签到
        userActivity.setStatus(UserActivitySignupStatusEnums.SIGNED_IN.getCode());
        // 更新
        userActivityService.updateById(userActivity);
    }
}
