package com.houjingyi.huodongbaoming.common.exception;

/**
 * 不打印日志异常
 *
 * @author yuelong.liang
 */
public class NoLogException extends RuntimeException {

    public NoLogException() {
        super();
    }

    public NoLogException(String message) {
        super(message);
    }

    public NoLogException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoLogException(Throwable cause) {
        super(cause);
    }

    protected NoLogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
