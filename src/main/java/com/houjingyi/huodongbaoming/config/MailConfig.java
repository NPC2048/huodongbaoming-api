package com.houjingyi.huodongbaoming.config;

import com.houjingyi.huodongbaoming.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 邮件发送配置
 *
 * @author yuelong.liang
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class MailConfig implements InitializingBean {

    private final MailProperties mailProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("========== 检测发送邮件服务器信息: ");
        log.info("==== 邮件服务器域名: " + mailProperties.getHost());
        log.info("==== 邮件服务器端口: " + mailProperties.getPort());
        log.info("==== 邮件服务器用户名: " + mailProperties.getUsername());
        log.info("==== 邮件服务器密码: " + mailProperties.getPassword());
        if (StringUtils.isEmpty(mailProperties.getUsername())) {
            throw new BusinessException("启动失败，请在配置文件或启动参数文件中配置邮件服务器用户");
        }
        if (StringUtils.isEmpty(mailProperties.getPassword())) {
            throw new BusinessException("启动失败，请在配置文件或启动参数中配置邮件服务器密码");
        }
    }
}
