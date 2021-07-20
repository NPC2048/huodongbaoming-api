package com.houjingyi.huodongbaoming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PreDestroy;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 全局配置，暂无
 *
 * @author yuelong.liang
 */
@Slf4j
public class GlobalConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        // 线程池，基本没用到，随便配一下
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(1);
        threadPool.setMaxPoolSize(2);
        threadPool.setQueueCapacity(32);
        threadPool.setDaemon(true);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPool;
    }

    @PreDestroy
    public void preDestroy() {
        log.info("程序优雅退出");
    }

}
