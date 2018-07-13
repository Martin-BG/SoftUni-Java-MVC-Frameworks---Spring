package com.softuni.residentevil.controllers;

import com.softuni.residentevil.models.binding.VirusAddBindingModel;
import com.softuni.residentevil.services.VirusService;
import com.softuni.residentevil.utils.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/viruses")
public final class VirusController extends BaseController {

    private final VirusService virusService;

    @Autowired
    public VirusController(
            final MessageWrapper messageWrapper,
            final VirusService virusService) {
        super(messageWrapper);
        this.virusService = virusService;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/viruses/all");
    }

    @GetMapping("/add")
    public ModelAndView addGet() {
        return super.view("/viruses/add", new VirusAddBindingModel());
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("viewModel") final VirusAddBindingModel virusAddBindingModel,
                                final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return super.view("/viruses/add", virusAddBindingModel);
        }

        if (!this.virusService.create(virusAddBindingModel)) {
            // TODO - proper error handling
            return super.view("/viruses/add", virusAddBindingModel);
        }

        return super.redirect("/viruses");
    }
}
