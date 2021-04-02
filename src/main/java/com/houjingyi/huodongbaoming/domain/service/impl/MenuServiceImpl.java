package com.houjingyi.huodongbaoming.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.common.util.AuthUtil;
import com.houjingyi.huodongbaoming.common.converter.MenuConverter;
import com.houjingyi.huodongbaoming.domain.entity.Menu;
import com.houjingyi.huodongbaoming.domain.mapper.MenuMapper;
import com.houjingyi.huodongbaoming.domain.model.vo.MenuVO;
import com.houjingyi.huodongbaoming.domain.service.MenuService;
import com.houjingyi.huodongbaoming.domain.service.base.impl.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单 Service 实现
 *
 * @author yuelong.liang
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuConverter menuConverter;

    @Override
    public List<MenuVO> listMenuByLoginId() {
        Long userId = StpUtil.getLoginIdAsLong();
        return menuConverter.toVOList(baseMapper.listMenuByUserId(userId));
    }
}
