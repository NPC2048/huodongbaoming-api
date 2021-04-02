package com.houjingyi.huodongbaoming.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import com.houjingyi.huodongbaoming.common.enums.ResultCodeEnum;
import com.houjingyi.huodongbaoming.common.exception.BusinessException;
import com.houjingyi.huodongbaoming.common.exception.NoLogException;
import com.houjingyi.huodongbaoming.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;

/**
 * controller 全局异常处理
 *
 * @author yuelong.liang
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 表单验证异常，不打印日志
     *
     * @param e e
     * @return R
     */
    @ExceptionHandler
    public R bindException(BindException e) {
        return R.failed(ResultCodeEnum.VALID_ERR, e.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 不打印日志异常
     *
     * @param e e
     * @return R
     */
    @ExceptionHandler
    public R noLogException(NoLogException e) {
        return R.failed(ResultCodeEnum.VALID_ERR, e.getMessage());
    }

    @ExceptionHandler
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.debug(e.getMessage());
        return R.failed(ResultCodeEnum.NOT_SUPPORT);
    }

    @ExceptionHandler
    public R notLoginException(NotLoginException e) {
        log.warn(e.getLoginKey() + ":" + e.getMessage());
        return R.failed(ResultCodeEnum.NO_LOGIN);
    }

    @ExceptionHandler
    public R messagingException(MessagingException e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return R.failed(e.getMessage());
    }

    @ExceptionHandler
    public R saTokenException(SaTokenException e) {
        log.debug(e.getMessage());
        return R.failed(ResultCodeEnum.LOGIN_TIMEOUT);
    }

    @ExceptionHandler
    public R noRoleException(NotRoleException e) {
        log.warn(e.getMessage());
        return R.failed(ResultCodeEnum.Forbidden);
    }

    @ExceptionHandler
    public R businessException(BusinessException e) {
        log.warn(e.getMessage(), e);
        return R.failed(e.getMessage());
    }

    @ExceptionHandler
    public R global(Exception e) {
        log.error(e.getMessage(), e);
        return R.failed("服务器错误");
    }
}
