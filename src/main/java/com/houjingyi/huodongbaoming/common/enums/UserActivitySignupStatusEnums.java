package com.houjingyi.huodongbaoming.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动报名状态枚举类
 *
 * @author yuelong.liang
 */
@Getter
@AllArgsConstructor
public enum UserActivitySignupStatusEnums {

    // 取消报名
    CANCEL((byte) 0, "取消报名"),
    ACTIVE((byte) 1, "生效中"),
    // 已签到
    SIGNED_IN((byte) 2, "已签到"),
    // 到期后用户如果没有参加活动，则会显示为逾期状态
    BE_OVERDUE((byte) 3, "逾期"),
    FINISH((byte) 4, "已完成"),
    ;

    private final Byte code;

    private final String name;

}
