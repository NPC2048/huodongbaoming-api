package com.houjingyi.huodongbaoming.common.form.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改用户信息表单
 *
 * @author yuelong.liang
 */
@Data
public class ModifyUserinfoForm {

    @NotBlank(message = "用户名不能为空")
    private String name;

    private Integer gender;

}
