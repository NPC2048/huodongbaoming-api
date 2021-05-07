package com.houjingyi.huodongbaoming.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

/**
 * 邮件工具类
 *
 * @author yuelong.liang
 */
@Component
@Slf4j
public class MailUtils {

    private static JavaMailSender mailSender;

    private static MailProperties mailProperties;

    @Autowired(required = false)
    public synchronized void setMailSender(JavaMailSender javaMailSender, MailProperties mailProperties) {
        MailUtils.mailSender = javaMailSender;
        MailUtils.mailProperties = mailProperties;
    }

    /**
     * 发送简单邮件
     *
     * @param subject 主题
     * @param text    内容
     * @param to      接收邮箱
     */
    public static void sendSimple(String subject, String text, String... to) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        log.info("========== Send Simple Mail: ");
        log.info("==== Subject: " + subject);
        log.info("==== Text: " + text);
        log.info("==== To: " + Arrays.toString(to));
        log.info("==== From: " + mailProperties.getUsername());
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setTo(to);
        mailMessage.setFrom(mailProperties.getUsername());
        mailSender.send(mailMessage);
    }

    /**
     * 发送复杂邮件
     *
     * @param subject 主题
     * @param text    内容
     * @param to      接收邮箱
     */
    public static void sendMime(String subject, String text, String... to) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false);
        message.setSubject(subject);
        message.setText(text, true);
        message.setTo(to);
        message.setFrom(mailProperties.getUsername());
        // 发送邮件
        log.info("========== Send Mime Mail Begin: ");
        log.info("==== Subject: " + subject);
        log.info("==== Text: " + text);
        log.info("==== To: " + Arrays.toString(to));
        log.info("==== From: " + mailProperties.getUsername());
        mailSender.send(mimeMessage);
        log.info("========== Send Mime Mail End. ");
    }

}
