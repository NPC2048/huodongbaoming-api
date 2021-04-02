package com.houjingyi.huodongbaoming.domain.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.houjingyi.huodongbaoming.common.exception.BusinessException;
import com.houjingyi.huodongbaoming.domain.entity.Role;
import com.houjingyi.huodongbaoming.domain.entity.UserRole;
import com.houjingyi.huodongbaoming.domain.mapper.UserRoleMapper;
import com.houjingyi.huodongbaoming.domain.service.RoleService;
import com.houjingyi.huodongbaoming.domain.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户角色 Service Impl
 *
 * @author yuelong.liang
 */
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final RoleService roleService;

    @Override
    public void join(Long userId, String roleName) {
        // 查询角色
        Role role = roleService.getOne(Wrappers.<Role>lambdaQuery().eq(Role::getName, roleName));
        if (role == null) {
            throw new BusinessException("关联角色失败，" + roleName + "不存在");
        }
        if (userId == null) {
            throw new BusinessException("关联角色失败, 用户 id 不能为空");
        }
        this.save(UserRole.builder().roleId(role.getId()).userId(userId).build());
    }
}
