package com.houjingyi.huodongbaoming.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 活动状态枚举类
 * 拒绝、未审核的活动，过了活动日期后，更新为过期状态
 * 通过的活动，过了活动日期后，更新为完成状态
 *
 * @author yuelong.liang
 */
@Getter
@AllArgsConstructor
public enum ActivityStatusEnums {

    // 未审核
    NOT_REVIEWED((byte) 0, "未审核"),
    // 通过审核
    PASS((byte) 1, "通过"),
    // 拒绝
    REFUSE((byte) 2, "拒绝"),
    // 过期
    BE_OVERDUE((byte) 3, "已过期"),
    // 完成
    FINISH((byte) 4, "完成"),
    // 进行中
    HAVE_IN_HAND((byte) 5, "进行中");

    private final Byte code;

    private final String name;

    public static ActivityStatusEnums valueOf(Byte status) {
        if (status == null) {
            return NOT_REVIEWED;
        }
        for (ActivityStatusEnums activityStatusEnums : values()) {
            if (activityStatusEnums.getCode().equals(status)) {
                return activityStatusEnums;
            }
        }
        return NOT_REVIEWED;
    }

}
