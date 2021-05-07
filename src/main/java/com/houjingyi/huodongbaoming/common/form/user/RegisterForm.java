package com.houjingyi.huodongbaoming.common.form.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 用户注册表单
 *
 * @author yuelong.liang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterForm extends MailCodeForm {

    /**
     * 邮箱验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

}
