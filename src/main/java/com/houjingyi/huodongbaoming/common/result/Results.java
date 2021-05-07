package com.houjingyi.huodongbaoming.common.result;

import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.enums.ResultStatusEnum;

/**
 * 统一返回值工具类
 *
 * @author yuelong.liang
 */
public class Results {

    private static final R DEFAULT_SUCCESS;

    static {
        DEFAULT_SUCCESS = R.builder().state(ResultStatusEnum.SUCCESS.getCode()).build();
    }

    private Results() {
    }

    /**
     * 返回成功, 不带数据
     *
     * @return R
     */
    public static R success() {
        return DEFAULT_SUCCESS;
    }

    /**
     * 返回成功，带有消息
     *
     * @param msg 消息
     * @return R
     */
    public static R success(String msg) {
        return R.builder()
                .state(ResultStatusEnum.SUCCESS.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 返回成功，带有数据
     *
     * @param data 数据
     * @return R
     */
    public static R success(Object data) {
        return R.builder()
                .state(ResultStatusEnum.SUCCESS.getCode())
                .data(data)
                .build();
    }

    /**
     * 返回表示错误的 result
     *
     * @param resultCode 错误代码
     * @param msg        消息
     * @return R
     */
    public static R failed(ResultCodeEnum resultCode, String msg) {
        return R.builder()
                .state(ResultStatusEnum.FAILED.getCode())
                .code(resultCode.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 返回表示失败的 result, 带有错误编码
     *
     * @param resultCode 错误编码
     * @return R
     */
    public static R failed(ResultCodeEnum resultCode) {
        return R.builder()
                .state(ResultStatusEnum.FAILED.getCode())
                .code(resultCode.getCode())
                .build();
    }

    /**
     * 返回错误消息，不带有编码
     *
     * @param msg 消息
     * @return R
     */
    public static R failed(String msg) {
        return R.builder()
                .state(ResultStatusEnum.FAILED.getCode())
                .msg(msg)
                .build();
    }

    /**
     * 根据传入的 state 返回成功或失败
     *
     * @param state boolean
     * @return R
     */
    public static R state(boolean state) {
        return R.builder()
                .state(state ? ResultStatusEnum.SUCCESS.getCode() : ResultStatusEnum.FAILED.getCode())
                .build();
    }
}
