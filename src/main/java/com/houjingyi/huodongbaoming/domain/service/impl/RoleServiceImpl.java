package com.houjingyi.huodongbaoming.domain.service.impl;

import com.houjingyi.huodongbaoming.domain.entity.Role;
import com.houjingyi.huodongbaoming.domain.mapper.RoleMapper;
import com.houjingyi.huodongbaoming.domain.service.RoleService;
import com.houjingyi.huodongbaoming.domain.service.base.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 角色 Service Impl
 *
 * @author yuelong.liang
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<String> listRoleNameByUserId(Serializable id) {
        return baseMapper.listRoleNameByUserId(id);
    }
}
