package com.houjingyi.huodongbaoming.domain.service;

import com.houjingyi.huodongbaoming.common.form.user.LoginForm;
import com.houjingyi.huodongbaoming.common.form.user.RegisterForm;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.service.base.BaseService;

/**
 * 用户 Service
 *
 * @author yuelong.liang
 */
public interface UserService extends BaseService<User> {

    /**
     * 用户注册
     *
     * @param form 表单
     * @return boolean
     */
    boolean register(RegisterForm form);

    /**
     * 校验手机号是否唯一
     *
     * @param phone 手机号
     * @return bool
     */
    boolean existsPhone(String phone);

    /**
     * 检查邮箱是否唯一
     *
     * @param email 邮箱
     * @return bool
     */
    boolean existsEmail(String email);

    boolean existsName(String name);

    /**
     * 登录
     *
     * @param form 表单
     * @return User
     */
    User loginByEmail(LoginForm form);

    /**
     * 检查管理员密码是否正确
     * @param password 密码原文
     * @return 检查结果
     */
    boolean checkAdminPassword(String password);
}
