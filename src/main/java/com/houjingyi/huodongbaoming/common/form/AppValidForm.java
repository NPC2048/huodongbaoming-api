package com.houjingyi.huodongbaoming.common.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * app 单个参数 form
 *
 * @author yuelong.liang
 */
@Data
public class AppValidForm<T> {

    @NotNull
    T data;

}
