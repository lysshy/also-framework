package com.also.framework.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key) {
        return this.getMessage(key, (Object[])null, key);
    }

    public String getMessage(String key, Object... args) {
        return this.getMessage(key, args, key);
    }

    private String getMessage(String key, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.messageSource.getMessage(key, args, defaultMessage, locale);
    }
}