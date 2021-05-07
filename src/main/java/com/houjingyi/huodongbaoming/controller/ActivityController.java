package com.houjingyi.huodongbaoming.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.houjingyi.huodongbaoming.common.enums.ActivityStatusEnums;
import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.form.AppDataForm;
import com.houjingyi.huodongbaoming.common.form.AppValidForm;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityPublishForm;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityQueryForm;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.common.result.Results;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.manage.ActivityManage;
import com.houjingyi.huodongbaoming.domain.model.vo.ActivityVO;
import com.houjingyi.huodongbaoming.domain.model.vo.UserEmailVO;
import com.houjingyi.huodongbaoming.domain.service.ActivityService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 活动 Controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/activity")
@AllArgsConstructor
@Validated
public class ActivityController {

    private final ActivityService activityService;

    private final ActivityManage activityManage;

    /**
     * 活动列表查询
     * - 普通用户只可查询状态为通过的活动
     *
     * @param form 查询表单
     * @return R
     */
    @PostMapping("/list")
    public R list(@RequestBody ActivityQueryForm form) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery()
                .eq(Activity::getStatus, ActivityStatusEnums.PASS.getCode());
        if (StringUtils.isNotEmpty(form.getKeyword())) {
            wrapper.and(lambda -> lambda.like(Activity::getTitle, form.getKeyword())
                    .or().like(Activity::getContent, form.getKeyword())
                    .or().like(Activity::getAddress, form.getKeyword()));
        }
        wrapper.orderByDesc(Activity::getUpdateTime).orderByDesc(Activity::getCreateTime);
        IPage<ActivityVO> page = activityService.page(form, wrapper);
        return Results.success(page);
    }


    /**
     * 查询自己发布的活动
     *
     * @param form 表单
     * @return R
     */
    @PostMapping("/my-publish")
    public R myPublish(@RequestBody ActivityQueryForm form) {
        LambdaQueryWrapper<Activity> wrapper = Wrappers.<Activity>lambdaQuery()
                .eq(Activity::getCreateUser, StpUtil.getLoginId());
        if (form.getStatus() != null) {
            wrapper.eq(Activity::getStatus, form.getStatus());
        }
        wrapper.orderByDesc(Activity::getUpdateTime).orderByDesc(Activity::getCreateTime);
        IPage<ActivityVO> page = activityService.page(form, wrapper);
        return Results.success(page);
    }

    /**
     * 查询自己报名的活动
     *
     * @param form 查询表单
     * @return R
     */
    @PostMapping("/my-signed-up")
    public R signedUpActivity(@RequestBody ActivityQueryForm form) {
        form.setUserId(StpUtil.getLoginIdAsLong());
        IPage<ActivityVO> page = activityService.pageBySignedUp(form, form);
        return Results.success(page);
    }

    /**
     * 根据活动 id 查询对应用户名和联系邮箱
     *
     * @param form 活动id表单
     * @return R
     */
    @PostMapping("/list-user")
    public R listUser(@Valid @RequestBody AppValidForm<Long> form) {
        Activity activity = activityService.getById(form.getData());
        if (activity == null) {
            return Results.failed("该活动不存在");
        }
        List<UserEmailVO> list = activityService.listUserEmail(form.getData());
        return Results.success(list);
    }

    /**
     * 查看活动详情
     *
     * @param id id
     * @return R
     */
    @GetMapping("/view")
    public R view(@RequestParam("id") Long id) {
        return Results.success(activityService.view(id));
    }

    /**
     * 报名活动， 使用当前登录用户
     *
     * @param form 活动 id
     * @return R
     */
    @PostMapping("/signup")
    public R signup(@RequestBody AppDataForm<Long> form) {
        if (form.getData() == null) {
            return Results.failed(ResultCodeEnum.VALID_ERR, "id不能为空");
        }
        activityManage.signup(form.getData());
        return Results.success();
    }

    /**
     * 活动报名取消
     *
     * @param id id
     * @return R
     */
    @PostMapping("/cancel")
    public R cancel(Long id) {
        activityManage.cancel(id);
        return Results.success();
    }

    /**
     * 活动签到
     * - 如果活动开始后未签到, 则活动结束后则将此用户报名的该活动报名记录标记为逾期（未参加）状态
     *
     * @param id 活动那个id
     * @return R
     */
    @PostMapping("/sign-in")
    public R signIn(Long id) {
        // 修改
        activityManage.signIn(id);
        return Results.success();
    }

    /**
     * 发布活动接口
     *
     * @param form 活动表单
     * @return R
     */
    @PostMapping("/publish")
    public R publish(@Valid @RequestBody ActivityPublishForm form) {
        return Results.state(activityService.publish(form));
    }

}
