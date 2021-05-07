package com.houjingyi.huodongbaoming.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.mapper.ActivityMapper;
import com.houjingyi.huodongbaoming.domain.service.ActivityServiceBySchedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 活动 Service 实现
 *
 * @author yuelong.liang
 */
@Service
public class ActivityServiceByScheduleImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityServiceBySchedule {

    /**
     * 处理实体类
     *
     * @param entity entity
     */
    public void resolveEntity(Activity entity) {
        LocalDateTime now = LocalDateTime.now();
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(now);
            entity.setCreateUser(GlobalConstants.ADMIN_ID);
        }
        entity.setUpdateTime(now);
        entity.setUpdateUser(GlobalConstants.ADMIN_ID);
    }

    @Override
    public boolean updateBatchById(Collection<Activity> entityList, int batchSize) {
        for (Activity activity : entityList) {
            this.resolveEntity(activity);
        }
        return super.updateBatchById(entityList, batchSize);
    }
}
