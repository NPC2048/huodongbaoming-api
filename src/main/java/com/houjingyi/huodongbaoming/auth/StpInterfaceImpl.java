package com.houjingyi.huodongbaoming.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;
import com.houjingyi.huodongbaoming.common.util.AuthUtil;
import com.houjingyi.huodongbaoming.domain.service.RoleService;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 获取用户角色和权限接口
 *
 * @author yuelong.liang
 */
@Component
@Slf4j
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final UserService userService;

    private final RoleService roleService;

    private final List<String> adminRoleList = Collections.singletonList("admin");

    @Override
    public List<String> getPermissionList(Object loginId, String loginKey) {
        log.info("========== loginId: " + loginId + ", loginKey: " + loginKey + " getPermissionList ==========");
        return Collections.emptyList();
    }

    /***
     * 获取角色列表，暂时写死
     * @param loginId loginId
     * @param loginKey loginKey
     * @return List
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> getRoleList(Object loginId, String loginKey) {
        log.info("========== loginId: " + loginId + ", loginKey: " + loginKey + " getRoleList ==========");
        boolean isAdmin = AuthUtil.isAdmin();
        log.info("========== isAdmin: " + isAdmin);
        Object role = StpUtil.getTokenSession().getAttribute(GlobalConstants.SESSION_ROLE);
        return (List<String>) role;
    }
}
