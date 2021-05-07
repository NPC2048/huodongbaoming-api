package com.houjingyi.huodongbaoming.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.houjingyi.huodongbaoming.common.enums.ActivityStatusEnums;
import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.exception.NoLogException;
import com.houjingyi.huodongbaoming.common.form.AppDataForm;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityQueryForm;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.common.result.Results;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.service.ActivityService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理审核活动
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
@Validated
public class AdminController {

    private final ActivityService activityService;

    /**
     * 管理员查看的活动列表
     *
     * @param form 查询表单
     * @return R
     */
    @PostMapping("/list")
    public R list(@RequestBody ActivityQueryForm form) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.lambdaQuery();
        // 标题、地址、内容等
        if (StringUtils.isNotEmpty(form.getKeyword())) {
            wrapper.like(Activity::getTitle, form.getKeyword())
                    .or().like(Activity::getAddress, form.getKeyword())
                    .or().like(Activity::getContent, form.getKeyword());
        }
        // 活动状态
        if (form.getStatus() != null) {
            wrapper.eq(Activity::getStatus, form.getStatus());
        }
        wrapper.orderByDesc(Activity::getUpdateTime).orderByDesc(Activity::getCreateTime);
        return Results.success(activityService.page(form, wrapper));
    }

    /**
     * 通过审核
     *
     * @param form 通过审核的活动 id
     * @return R
     */
    @PostMapping("/pass")
    public R pass(@RequestBody AppDataForm<List<Long>> form) {
        if (CollectionUtils.isEmpty(form.getData())) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "请选择活动");
        }
        List<Activity> activityList = activityService.listByIds(form.getData());
        // 修改状态
        activityList.forEach(activity -> {
            checkStatus(activity);
            activity.setStatus(ActivityStatusEnums.PASS.getCode());
        });
        // 持久化到数据库
        activityService.updateBatchById(activityList);
        return Results.success();
    }

    /**
     * 拒绝通过
     *
     * @param form 活动 id 集合
     * @return R
     */
    @PostMapping("/refuse")
    public R refuse(@RequestBody AppDataForm<List<Long>> form) {
        if (CollectionUtils.isEmpty(form.getData())) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "请选择活动");
        }
        List<Activity> list = activityService.listByIds(form.getData());
        // 修改状态
        list.forEach(activity -> {
            if (ActivityStatusEnums.REFUSE.getCode().equals(activity.getStatus())) {
                throw new NoLogException("选择的活动中含有已拒绝的活动，请重新选择");
            }
            checkStatus(activity);
            activity.setStatus(ActivityStatusEnums.REFUSE.getCode());
        });
        // 持久化到数据库
        activityService.updateBatchById(list);
        return Results.success();
    }

    private void checkStatus(Activity activity) {
        if (ActivityStatusEnums.PASS.getCode().equals(activity.getStatus())) {
            throw new NoLogException("选择的活动中含有已通过的活动，请重新选择");
        }
        if (ActivityStatusEnums.BE_OVERDUE.getCode().equals(activity.getStatus())) {
            throw new NoLogException("选择的活动中含有已过期的活动，请重新选择");
        }
        if (ActivityStatusEnums.HAVE_IN_HAND.getCode().equals(activity.getStatus())) {
            throw new NoLogException("选择的活动中含有进行中的活动，请重新选择");
        }
        if (ActivityStatusEnums.FINISH.getCode().equals(activity.getStatus())) {
            throw new NoLogException("选择的活动中含有已完成的活动，请重新选择");
        }
    }

}
