package com.houjingyi.huodongbaoming.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 头像代理设置
 *
 * @author yuelong.liang
 */
@ConfigurationProperties(prefix = "gravatar")
@Data
public class GravatarConfig {

    /**
     * 代理域名
     */
    private String proxy;

}
