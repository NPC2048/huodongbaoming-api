package com.houjingyi.huodongbaoming.common.form.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码表单
 *
 * @author yuelong.liang
 */
@Data
public class ModifyPassForm {

    @NotBlank(message = "旧密码不能为空")
    private String oldPass;

    @NotBlank(message = "新密码不能为空")
    private String newPass;

    @NotBlank(message = "请确认新密码")
    private String confirmPass;

}

