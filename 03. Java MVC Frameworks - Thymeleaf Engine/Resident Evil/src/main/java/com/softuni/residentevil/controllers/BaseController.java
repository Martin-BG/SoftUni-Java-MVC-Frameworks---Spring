package com.softuni.residentevil.controllers;

import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

abstract class BaseController {

    private static final String BASE_PAGE_LAYOUT = "/fragments/base-layout";
    private static final String PROPERTY_VIEW_NAME = "viewName";
    private static final String PROPERTY_VIEW_MODEL = "viewModel";
    private static final String PROPERTY_TITLE = "title";
    private static final String REDIRECT_KEYWORD = "redirect:";

    private final MessageWrapper messageWrapper;

    protected BaseController(final MessageWrapper messageWrapper) {
        this.messageWrapper = messageWrapper;
    }

    protected ModelAndView view(String viewName, Object viewModel, String title) {
        if (title == null) {
            title = this.messageWrapper.getMessage("application.title");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(BASE_PAGE_LAYOUT);
        modelAndView.addObject(PROPERTY_VIEW_NAME, viewName);
        modelAndView.addObject(PROPERTY_VIEW_MODEL, viewModel);
        modelAndView.addObject(PROPERTY_TITLE, title);

        return modelAndView;
    }

    protected ModelAndView view(String viewName, String title) {
        return this.view(viewName, null, title);
    }

    protected ModelAndView view(String viewName, Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    protected ModelAndView view(String viewName) {
        return this.view(viewName, null, null);
    }

    protected ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(REDIRECT_KEYWORD + redirectUrl);

        return modelAndView;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false)); //Trim form input strings
    }
}
