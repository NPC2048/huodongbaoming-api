package com.houjingyi.huodongbaoming.domain.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityPublishForm;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityQueryForm;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.entity.UserActivity;
import com.houjingyi.huodongbaoming.domain.model.vo.ActivityVO;
import com.houjingyi.huodongbaoming.domain.model.vo.UserEmailVO;
import com.houjingyi.huodongbaoming.domain.model.vo.UserinfoVO;
import com.houjingyi.huodongbaoming.domain.service.base.BaseService;

import java.util.List;

/**
 * 活动 Service
 *
 * @author yuelong.liang
 */
public interface ActivityService extends BaseService<Activity> {

    /**
     * 活动查询
     *
     * @param page    分页参数
     * @param wrapper 查询条件
     * @return Page
     */
    IPage<ActivityVO> page(IPage<Activity> page, LambdaQueryWrapper<Activity> wrapper);

    /**
     * 分页查询当前登录用户已报名的活动
     *
     * @param page 分页参数
     * @param form 查询条件
     * @return IPage
     */
    IPage<ActivityVO> pageBySignedUp(IPage<Activity> page, ActivityQueryForm form);

    /**
     * 根据 id 查询活动
     *
     * @param id id
     * @return ActivityVO
     */
    ActivityVO view(Long id);

    /**
     * 发布活动
     *
     * @param form form
     * @return boolean
     */
    boolean publish(ActivityPublishForm form);

    /**
     * 根据活动 id 查找对应的用户名与联系信息
     *
     * @param id 活动 id
     * @return List
     */
    List<UserEmailVO> listUserEmail(Long id);
}
