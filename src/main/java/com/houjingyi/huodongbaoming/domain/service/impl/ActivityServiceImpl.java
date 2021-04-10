package com.houjingyi.huodongbaoming.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.houjingyi.huodongbaoming.common.converter.ActivityConverter;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityPublishForm;
import com.houjingyi.huodongbaoming.common.form.activity.ActivityQueryForm;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.mapper.ActivityMapper;
import com.houjingyi.huodongbaoming.domain.model.vo.ActivityVO;
import com.houjingyi.huodongbaoming.domain.model.vo.UserEmailVO;
import com.houjingyi.huodongbaoming.domain.service.ActivityService;
import com.houjingyi.huodongbaoming.domain.service.base.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yuelong.liang
 */
@Service
@AllArgsConstructor
public class ActivityServiceImpl extends BaseServiceImpl<ActivityMapper, Activity> implements ActivityService {

    private final ActivityConverter activityConverter;

    @Override
    public IPage<ActivityVO> page(IPage<Activity> page, LambdaQueryWrapper<Activity> wrapper) {
        return baseMapper.page(page, wrapper);
    }

    @Override
    public IPage<ActivityVO> pageBySignedUp(IPage<Activity> page, ActivityQueryForm form) {
        return baseMapper.pageBySignedUp(page, form);
    }

    @Override
    public ActivityVO view(Long id) {
        Activity entity = baseMapper.selectOne(Wrappers.<Activity>lambdaQuery()
                .select(Activity::getId, Activity::getTitle, Activity::getContent,
                        Activity::getAddress, Activity::getDate, Activity::getNum, Activity::getLimitNum,
                        Activity::getStatus)
                .eq(Activity::getId, id)
        );
        return entity == null ? null : activityConverter.toVO(entity);
    }

    @Override
    public boolean publish(ActivityPublishForm form) {
        Activity entity = activityConverter.toEntity(form);
        // 关联用户
        entity.setPublishUser(StpUtil.getLoginIdAsLong());
        return this.save(entity);
    }

    @Override
    public List<UserEmailVO> listUserEmail(Long id) {
        return baseMapper.listUserEmail(id);
    }

}
