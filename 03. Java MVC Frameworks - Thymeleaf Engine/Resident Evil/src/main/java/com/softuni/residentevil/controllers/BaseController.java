package com.softuni.residentevil.controllers;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    private static final String DEFAULT_TITLE = "Viruses";

    public ModelAndView view(String viewName, Object viewModel, String title) {
        if (title == null) {
            title = DEFAULT_TITLE;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/fragments/base-layout");
        modelAndView.addObject("viewName", viewName);
        modelAndView.addObject("viewModel", viewModel);
        modelAndView.addObject("title", title);

        return modelAndView;
    }

    public ModelAndView view(String viewName, String title) {
        return this.view(viewName, null, title);
    }

    public ModelAndView view(String viewName, Object viewModel) {
        return this.view(viewName, viewModel, null);
    }

    public ModelAndView view(String viewName) {
        return this.view(viewName, null, null);
    }

    public ModelAndView redirect(String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("redirect:" + redirectUrl);

        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false)); //Trim form input strings
    }
}
