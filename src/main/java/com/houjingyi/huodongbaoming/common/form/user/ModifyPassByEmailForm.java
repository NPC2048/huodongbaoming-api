package com.houjingyi.huodongbaoming.common.form.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 忘记密码-修改密码提交表单
 *
 * @author yuelong.liang
 */
@Data
public class ModifyPassByEmailForm {

    /**
     * 用户名或邮箱
     */
    @NotBlank(message = "用户名或邮箱不能为空")
    private String name;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPass;

    @NotBlank(message = "请确认新密码")
    private String confirmPass;

    @NotBlank(message = "邮箱验证码不能为空")
    private String code;

}
