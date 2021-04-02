package com.houjingyi.huodongbaoming.common.form.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 登录表单
 *
 * @author yuelong.liang
 */
@Data
public class LoginForm {

    @NotBlank(message = "用户名或邮箱不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
