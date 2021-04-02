package com.houjingyi.huodongbaoming.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.houjingyi.huodongbaoming.domain.entity.UserRole;

/**
 * 用户橘色 Service
 *
 * @author yuelong.liang
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 关联用户
     *
     * @param userId   用户id
     * @param roleName 角色名称
     */
    void join(Long userId, String roleName);

}
