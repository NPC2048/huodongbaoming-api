package com.houjingyi.huodongbaoming.common.constant;

/**
 * 邮件模板常量类
 *
 * @author yuelong.liang
 */
public class MailTemplateConstant {

    private MailTemplateConstant() {
    }

    /**
     * 注册模板
     */
    public static final String REGISTER = "您的验证码是：<span style='color: #66ccff'>{0}</span> <br> 3 分钟内有效";

    /**
     * 修改密码模板
     */
    public static final String MODIFY_PASS = "您正在修改密码，如果不是本人操作，请注意账号安全。<br /> 您的验证码是：<span style='color: #66ccff'>{0}</span> <br> 3 分钟内有效";

    /**
     * 修改头像/邮箱
     */
    public static final String MODIFY_EMAIL = "您正在修改头像/邮箱, 您的验证码是：<span style='color: #66ccff'>{0}</span> <br> 3 分钟内有效";
}
