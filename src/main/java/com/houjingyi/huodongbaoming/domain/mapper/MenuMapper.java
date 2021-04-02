package com.houjingyi.huodongbaoming.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.houjingyi.huodongbaoming.domain.entity.Menu;

import java.util.List;

/**
 * 菜单 Mapper
 *
 * @author yuelong.liang
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户 id 查询菜单权限
     *
     * @param userId 用户 id
     * @return List
     */
    List<Menu> listMenuByUserId(Long userId);

}
