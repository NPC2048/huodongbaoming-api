package com.houjingyi.huodongbaoming.schedule;

import com.houjingyi.huodongbaoming.common.enums.ActivityStatusEnums;
import com.houjingyi.huodongbaoming.domain.entity.Activity;
import com.houjingyi.huodongbaoming.domain.service.ActivityServiceBySchedule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描已开始或已过期的活动，进行处理
 *
 * @author yuelong.liang
 */
@Component
@AllArgsConstructor
@Slf4j
public class ActivitySchedule {

    private final ActivityServiceBySchedule activityService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void activityScan() {
        // 如果未登录，切换为管理员
        List<Activity> activities = activityService.lambdaQuery()
                .ne(Activity::getStatus, ActivityStatusEnums.FINISH.getCode())
                .ne(Activity::getStatus, ActivityStatusEnums.BE_OVERDUE.getCode())
                // 活动日期小于当前时间
                .le(Activity::getDate, LocalDate.now().plusDays(1).atStartOfDay())
                .list();
        List<Activity> updateList = new ArrayList<>(activities.size());
        for (Activity activity : activities) {
            ActivityStatusEnums statusEnums = ActivityStatusEnums.valueOf(activity.getStatus());
            // 是否已超过活动时间
            switch (statusEnums) {
                case PASS:
                    // 如果已超过活动时间，则将状态改为开始状态
                    if (activity.getDate().isBefore(LocalDate.now().atStartOfDay())) {
                        activity.setStatus(ActivityStatusEnums.HAVE_IN_HAND.getCode());
                        updateList.add(activity);
                    }
                    break;
                case NOT_REVIEWED:
                    if (activity.getDate().isBefore(LocalDate.now().atStartOfDay())) {
                        // 一直未通过审核，改为过期状态
                        activity.setStatus(ActivityStatusEnums.BE_OVERDUE.getCode());
                        updateList.add(activity);
                    }
                    break;
                case HAVE_IN_HAND:
                    // 活动日期 11, 当前时间 12
                    if (activity.getDate().isBefore(LocalDate.now().plusDays(1).atStartOfDay())) {
                        // 改为完成状态
                        activity.setStatus(ActivityStatusEnums.FINISH.getCode());
                        updateList.add(activity);
                    }
                    break;
                default:
            }
        }
        // 更新活动
        this.activityService.updateBatchById(updateList);
    }

}
