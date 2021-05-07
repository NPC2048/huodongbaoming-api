package com.houjingyi.huodongbaoming;


import cn.hutool.crypto.digest.DigestUtil;
import com.houjingyi.huodongbaoming.domain.entity.User;
import com.houjingyi.huodongbaoming.domain.service.UserService;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HuodongbaomingApplicationTests {

    @Resource
    private UserService userService;

    @Test
    @SuppressWarnings("squid:S2699")
    void contextLoads() {
    }

}
