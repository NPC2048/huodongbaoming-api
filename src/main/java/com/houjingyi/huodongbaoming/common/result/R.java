package com.houjingyi.huodongbaoming.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回值
 *
 * @author yuelong.liang
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class R {

    /**
     * 返回 1 为 success
     * 0 或其他未 failed
     */
    protected Byte state;

    /**
     * 返回代码，标志什么类型的错误, 前端维护对应的表处理错误
     */
    protected String code;

    /**
     * 消息
     */
    protected String msg;

    /**
     * 具体数据
     */
    protected Object data;

}
