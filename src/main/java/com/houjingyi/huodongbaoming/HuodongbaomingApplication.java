package com.houjingyi.huodongbaoming;

import com.houjingyi.huodongbaoming.config.*;
import lombok.Getter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 *
 * @author yuelong.liang
 */
@EnableScheduling
@MapperScan("com.houjingyi.huodongbaoming.domain.mapper")
@Configuration
@Import({
        GlobalConfig.class,
        GravatarConfig.class,
        MailConfig.class,
        MybatisPlusConfig.class,
        SaTokenConfig.class
})
@SpringBootApplication(exclude = {
        MultipartAutoConfiguration.class,
        JmxAutoConfiguration.class
})
public class HuodongbaomingApplication {

    @Getter
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        HuodongbaomingApplication.context = SpringApplication.run(HuodongbaomingApplication.class, args);
    }

}
