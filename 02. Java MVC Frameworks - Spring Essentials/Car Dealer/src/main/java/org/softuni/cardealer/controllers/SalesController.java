package org.softuni.cardealer.controllers;

import org.softuni.cardealer.services.CarService;
import org.softuni.cardealer.utils.MessageWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sales")
public class SalesController extends BaseController {

    protected SalesController(final MessageWrapper messageWrapper,
                              final CarService carService) {
        super(messageWrapper, carService);
    }
}
