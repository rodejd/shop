package com.wepinit.wepinit_shop.config;

import com.wepinit.wepinit_shop.xmall.common.util.MessageAccessorUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 서버올라가면 message.error_message 가 동작하는듯 하다.
        messageSource.setBasename("messages/message");      // main/resources/messages/message_*
//        messageSource.setBasename("message.error_message");
        messageSource.setDefaultEncoding("UTF-8");

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }

    @Bean
    public MessageAccessorUtils message() {
        MessageAccessorUtils utils = new MessageAccessorUtils();
        utils.setMessageSourceAccessor(messageSourceAccessor());

        return utils;
    }
}
