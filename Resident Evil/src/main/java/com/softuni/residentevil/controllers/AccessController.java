package com.softuni.residentevil.controllers;

import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController extends BaseController {

    protected AccessController(final MessageWrapper messageWrapper) {
        super(messageWrapper);
    }

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "/error/unauthorized";
    }
}
