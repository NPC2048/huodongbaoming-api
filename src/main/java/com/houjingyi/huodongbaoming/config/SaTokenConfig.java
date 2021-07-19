package com.houjingyi.huodongbaoming.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token 拦截器
 *
 * @author yuelong.liang
 */
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 进入页面是显示默认底部导航。登陆后重新加载菜单
        registry.addInterceptor(SaRouteInterceptor.createLoginVal())
                .excludePathPatterns("/user/login-email", "/user/login-email", "/activity/list", "/menu/menus",
                        "/user/mail-code", "/user/register", "/user/forget-code", "/user/modify-pass-by-email")
                .addPathPatterns("/**");
        // 活动管理需要管理员角色
        registry.addInterceptor(SaRouteInterceptor.createRoleVal("admin")).addPathPatterns("/admin/**");
    }
}
