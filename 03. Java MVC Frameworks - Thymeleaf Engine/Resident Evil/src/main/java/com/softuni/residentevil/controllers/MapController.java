package com.softuni.residentevil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/map")
public class MapController extends BaseController {

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/map/view");
    }
}
