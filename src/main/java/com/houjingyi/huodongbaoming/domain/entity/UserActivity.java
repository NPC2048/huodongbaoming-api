package com.houjingyi.huodongbaoming.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.houjingyi.huodongbaoming.common.enums.UserActivitySignupStatusEnums;
import com.houjingyi.huodongbaoming.domain.entity.base.BaseIdEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 用户-活动中间表实体类
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("hd_user_activity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActivity extends BaseIdEntity {

    /**
     * 用户 id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 活动 id
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * 报名日期
     */
    @TableField("signup_date")
    private LocalDateTime signupDate;

    /**
     * 活动日期
     */
    @TableField("activity_date")
    private LocalDateTime activityDate;

    /**
     * 状态
     *
     * @see UserActivitySignupStatusEnums
     */
    @TableField("status")
    private Byte status;

}
