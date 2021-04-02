package com.houjingyi.huodongbaoming.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.common.converter.UserConverter;
import com.houjingyi.huodongbaoming.common.form.user.LoginForm;
import com.houjingyi.huodongbaoming.common.form.user.RegisterForm;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.mapper.UserMapper;
import com.houjingyi.huodongbaoming.domain.service.UserRoleService;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import com.houjingyi.huodongbaoming.domain.service.base.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 用户 Service 实现
 *
 * @author yuelong.liang
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    private final UserConverter userConverter;

    private final UserRoleService userRoleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(RegisterForm form) {
        User entity = userConverter.toEntity(form);
        // 密码转 sha256
        entity.setPassword(DigestUtil.sha256Hex(form.getPassword()));
        // 设置默认角色
        boolean result = this.save(entity);
        if (result) {
            // 关联角色
            userRoleService.join(entity.getId(), GlobalConstants.DEFAULT_ROLE);
        }
        return result;
    }

    @Override
    public boolean existsPhone(String phone) {
        return SqlHelper.retCount(baseMapper.selectCount(Wrappers.<User>lambdaQuery().eq(User::getPhone, phone))) > 0;
    }

    @Override
    public boolean existsEmail(String email) {
        return SqlHelper.retCount(lambdaQuery().eq(User::getEmail, email).count()) > 0;
    }

    @Override
    public boolean existsName(String name) {
        return SqlHelper.retCount(this.lambdaQuery().eq(User::getName, name).count()) > 0;
    }

    @Override
    public User loginByEmail(LoginForm form) {
        return baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getName, form.getUsername())
                .eq(User::getPassword, DigestUtil.sha256Hex(form.getPassword())));
    }

    @Override
    public void resolveEntity(User entity) {
        LocalDateTime now = LocalDateTime.now();
        Long userId;
        if (StpUtil.isLogin()) {
            userId = StpUtil.getLoginIdAsLong();
        } else {
            userId = null;
        }
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(now);
            // 系统管理员
            entity.setCreateUser(userId);
        }
        entity.setUpdateTime(now);
        // 系统管理员
        entity.setUpdateUser(userId);
    }
}
