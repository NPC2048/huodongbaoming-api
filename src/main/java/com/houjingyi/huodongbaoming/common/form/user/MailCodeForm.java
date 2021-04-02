package com.houjingyi.huodongbaoming.common.form.user;

import com.houjingyi.huodongbaoming.common.constant.RegexpConstants;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 获取邮件表单
 *
 * @author yuelong.liang
 */
@Data
public class MailCodeForm {

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 手机号
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式错误", regexp = RegexpConstants.EMAIL)
    private String email;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String confirmPass;


}
