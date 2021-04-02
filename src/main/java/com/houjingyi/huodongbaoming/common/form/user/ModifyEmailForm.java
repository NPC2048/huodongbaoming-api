package com.houjingyi.huodongbaoming.common.form.user;

import com.houjingyi.huodongbaoming.common.constant.RegexpConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 更改邮箱表单
 *
 * @author yuelong.liang
 */
@Data
public class ModifyEmailForm {

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误", regexp = RegexpConstants.EMAIL)
    private String email;

    @NotBlank(message = "邮箱验证码不能为空")
    private String code;

}
