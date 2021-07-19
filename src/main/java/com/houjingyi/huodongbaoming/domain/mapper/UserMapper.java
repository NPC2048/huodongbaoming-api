package com.houjingyi.huodongbaoming.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houjingyi.huodongbaoming.domain.entity.User;

/**
 * 用户 Mapper
 *
 * @author yuelong.liang
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名和密码查询用户信息
     * - 关联查询菜单
     * - 关联查询角色
     *
     * @param username 用户
     * @param password 经过 sha256 的密码
     * @return User
     */
    User get(String username, String password);

}
