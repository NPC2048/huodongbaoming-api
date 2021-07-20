package com.houjingyi.huodongbaoming.controller;

import com.houjingyi.huodongbaoming.HuodongbaomingApplication;
import com.houjingyi.huodongbaoming.common.result.R;
import com.houjingyi.huodongbaoming.common.result.Results;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统控制 controller
 *
 * @author yuelong.liang
 */
@RequestMapping("/system")
@RequiredArgsConstructor
@RestController
public class SystemController {

    private final UserService userService;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 传入admin密码进行校验
     * 正确后关闭应用程序
     *
     * @param password password
     * @return R
     */
    @GetMapping("/shutdown")
    public R shutdown(String password) {
        boolean bool = userService.checkAdminPassword(password);
        if (bool) {
            threadPoolTaskExecutor.execute(() -> {
                int exitCode = SpringApplication.exit(HuodongbaomingApplication.getContext(), () -> 0);
                System.exit(exitCode);
            }, 1000);
        }
        return bool ? Results.success() : Results.failed("密码错误");
    }

}
