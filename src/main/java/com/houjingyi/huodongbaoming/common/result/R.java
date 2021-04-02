package com.houjingyi.huodongbaoming.common.result;

import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;

import static org.apache.commons.lang3.math.NumberUtils.BYTE_ONE;
import static org.apache.commons.lang3.math.NumberUtils.BYTE_ZERO;

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

    public static final R SUCCESS;

    public static final R FAILED;

    public static final R NO_LOGIN;

    static {
        SUCCESS = new R(BYTE_ONE);
        FAILED = new R(BYTE_ZERO);
        NO_LOGIN = new R(BYTE_ZERO, ResultCodeEnum.NO_LOGIN);
    }

    /**
     * 返回 1 为 success
     * 0 或其他未 failed
     */
    protected Byte state;

    /**
     * 返回代码，标志什么类型的错误, 前端维护对应的表处理错误
     */
    protected String code;

    protected String msg;

    protected Object data;

    public R(boolean isSuccess) {
        this.state = isSuccess ? BYTE_ONE : NumberUtils.BYTE_ZERO;
    }

    public R(boolean isSuccess, String msg, Object data) {
        this(isSuccess);
        this.msg = msg;
        this.data = data;
    }

    public R(Byte state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public R(Byte state, Object data) {
        this.state = state;
        this.data = data;
    }

    public R(Byte state) {
        this.state = state;
    }

    public R(Byte state, ResultCodeEnum resultCodeEnum) {
        this.state = state;
        this.code = resultCodeEnum.getCode();
    }

    public static R success(String msg) {
        return new R(BYTE_ONE, msg);
    }

    public static R success(Object data) {
        return new R(BYTE_ONE, data);
    }

    public static R failed(String msg) {
        return new R(BYTE_ZERO, msg);
    }

    public static R state(boolean state) {
        return new R(state);
    }

    public static R result(Exception e) {
        return new R(BYTE_ZERO, e.getMessage());
    }

    public static R result(String msg) {
        return new R(BYTE_ONE, msg);
    }

    public static R result(Object data) {
        return new R(BYTE_ONE, data);
    }

    public static R failed(ResultCodeEnum code) {
        return new R(BYTE_ZERO, code);
    }

    public static R failed(ResultCodeEnum code, String msg) {
        return R.builder().state(BYTE_ZERO).code(code.getCode()).msg(msg).build();
    }
}
