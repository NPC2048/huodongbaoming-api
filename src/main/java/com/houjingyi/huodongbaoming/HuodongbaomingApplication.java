package com.houjingyi.huodongbaoming;

import com.houjingyi.huodongbaoming.config.GravatarConfig;
import com.houjingyi.huodongbaoming.config.MailConfig;
import com.houjingyi.huodongbaoming.config.MybatisPlusConfig;
import com.houjingyi.huodongbaoming.config.SaTokenConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
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

    public static void main(String[] args) {
        SpringApplication.run(HuodongbaomingApplication.class, args);
    }

}
