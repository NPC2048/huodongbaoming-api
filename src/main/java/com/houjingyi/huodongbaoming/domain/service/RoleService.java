package com.houjingyi.huodongbaoming.domain.service;

import com.houjingyi.huodongbaoming.domain.entity.Role;
import com.houjingyi.huodongbaoming.domain.service.base.BaseService;
import com.houjingyi.huodongbaoming.domain.service.base.BaseVoService;

import java.io.Serializable;
import java.util.List;

/**
 * 角色 Service
 *
 * @author yuelong.liang
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 根据用户 id 查询角色列表
     *
     * @param id 用户 id
     * @return List
     */
    List<String> listRoleNameByUserId(Serializable id);

}
