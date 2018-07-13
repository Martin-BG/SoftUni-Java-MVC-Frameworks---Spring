package com.softuni.residentevil.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageWrapper {

    private final MessageSource messageSource;

    @Autowired
    public MessageWrapper(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String code, Object... args) {
        return this.messageSource.getMessage(code, args, LocaleContextHolder.getLocale()); // Locale.getDefault()
    }

    public String getMessage(String code) {
        return this.getMessage(code, null);
    }
}
