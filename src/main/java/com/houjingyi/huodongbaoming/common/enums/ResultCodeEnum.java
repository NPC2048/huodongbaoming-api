package com.houjingyi.huodongbaoming.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuelong.liang
 */

@AllArgsConstructor
public enum ResultCodeEnum {

    // 未登录
    NO_LOGIN("2"),
    // 用户名或密码错误
    INVALID_USER("3"),
    // 表单验证异常
    VALID_ERR("4"),
    // 登录已过期
    LOGIN_TIMEOUT("5"),
    NOT_SUPPORT("405"),
    // 无权限访问
    Forbidden("403"),
    // 系统错误
    SYS_ERROR("999"),
    ;

    @Getter
    @Setter
    private String code;

}
