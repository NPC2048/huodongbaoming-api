package com.houjingyi.huodongbaoming.common.util;

import cn.dev33.satoken.stp.StpUtil;
import com.houjingyi.huodongbaoming.common.constant.GlobalConstants;

/**
 * 权限工具类
 *
 * @author yuelong.liang
 */
public class AuthUtil {

    private AuthUtil() {
    }

    /**
     * 判断当前登录的用户是否为管理员
     *
     * @return boolean
     */
    public static boolean isAdmin() {
        Object isAdmin = StpUtil.getSession().getAttribute(GlobalConstants.SESSION_IS_ADMIN);
        return isAdmin != null;
    }

}
