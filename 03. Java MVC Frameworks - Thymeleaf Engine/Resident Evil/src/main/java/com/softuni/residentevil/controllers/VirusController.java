package com.softuni.residentevil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viruses")
public class VirusController extends BaseController {

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/viruses/all");
    }

    @GetMapping("/add")
    public ModelAndView addGet() {
        return super.view("/viruses/add");
    }

    @PostMapping("/add")
    public ModelAndView addPost() {
        return super.view("/viruses/add");
    }
}
