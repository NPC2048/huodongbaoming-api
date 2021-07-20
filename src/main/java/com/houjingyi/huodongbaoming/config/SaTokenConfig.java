package com.houjingyi.huodongbaoming.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * sa-token 拦截器
 *
 * @author yuelong.liang
 */
@ConfigurationProperties(prefix = "route")
@Data
@Slf4j
public class SaTokenConfig implements WebMvcConfigurer {

    private List<String> authList;

    private List<String> excludeList;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 进入页面是显示默认底部导航。登陆后重新加载菜单
        log.info("需要登陆的路径: {}", authList);
        log.info("排除的路径: {}", excludeList);

        registry.addInterceptor(new SaRouteInterceptor((saRequest, saResponse, o) -> {
            SaRouter.match(authList, excludeList, StpUtil::checkLogin);
            // 活动管理需要管理员角色
            SaRouter.match("/admin/**", () -> StpUtil.checkPermission("admin"));
        }));
    }
}
