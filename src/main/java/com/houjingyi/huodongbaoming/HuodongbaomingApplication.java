package com.houjingyi.huodongbaoming;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author yuelong.liang
 */
@EnableScheduling
@MapperScan("com.houjingyi.huodongbaoming.domain.mapper")
@SpringBootApplication
public class HuodongbaomingApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuodongbaomingApplication.class, args);
    }

}
