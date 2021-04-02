package com.houjingyi.huodongbaoming.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houjingyi.huodongbaoming.domain.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * 角色 Mapper
 *
 * @author yuelong.liang
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户 id 查询角色列表
     *
     * @param id id
     * @return List
     */
    List<String> listRoleNameByUserId(Serializable id);

}