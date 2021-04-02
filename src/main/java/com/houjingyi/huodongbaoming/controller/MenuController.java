package com.houjingyi.huodongbaoming.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.domain.service.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * 菜单 Controller
 *
 * @author yuelong.liang
 */
@RestController
@RequestMapping("/menu")
@AllArgsConstructor
@Validated
public class MenuController {

    private final MenuService menuService;

    /**
     * 根据当前登录用户获取菜单
     *
     * @return R
     */
    @GetMapping("/menus")
    public R menus() {
        if (!StpUtil.isLogin()) {
            return R.success(Collections.emptyList());
        }
        return R.success(menuService.listMenuByLoginId());
    }

}
