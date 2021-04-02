package com.houjingyi.huodongbaoming.domain.service;

import com.houjingyi.huodongbaoming.domain.entity.Menu;
import com.houjingyi.huodongbaoming.domain.model.vo.MenuVO;
import com.houjingyi.huodongbaoming.domain.service.base.BaseService;

import java.util.List;

/**
 * 菜单 Service
 *
 * @author yuelong.liang
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 获取菜单列表
     * 根据当前登录用户获取菜单列表
     *
     * @return List 菜单 List
     * @throws cn.dev33.satoken.exception.NotLoginException 未登录异常
     */
    List<MenuVO> listMenuByLoginId();
}
