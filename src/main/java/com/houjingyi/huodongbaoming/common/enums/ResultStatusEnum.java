package com.houjingyi.huodongbaoming.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * REST 返回状态枚举类
 *
 * @author yuelong.liang
 */
@AllArgsConstructor
public enum ResultStatusEnum {

    // 成功
    SUCCESS((byte) 1),
    // 失败
    FAILED((byte) 0);

    @Getter
    private final Byte code;

}
