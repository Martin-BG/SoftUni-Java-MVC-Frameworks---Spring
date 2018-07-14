package com.softuni.residentevil.controllers;

import com.softuni.residentevil.etities.enums.Magnitude;
import com.softuni.residentevil.etities.enums.Mutation;
import com.softuni.residentevil.models.binding.VirusAddBindingModel;
import com.softuni.residentevil.services.CapitalService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/viruses")
public final class VirusController extends BaseController {

    private final VirusService virusService;
    private final CapitalService capitalsService;

    @Autowired
    public VirusController(
            final MessageWrapper messageWrapper,
            final VirusService virusService,
            final CapitalService capitalsService) {
        super(messageWrapper);
        this.virusService = virusService;
        this.capitalsService = capitalsService;
    }

    @GetMapping(value = {"", "/"})
    public ModelAndView rootGet() {
        return super.view("/viruses/all");
    }

    @GetMapping("/add")
    public ModelAndView addGet() {
        final VirusAddBindingModel virusDto = this.loadDataToViewModel(new VirusAddBindingModel());
        return super.view("/viruses/add", virusDto);
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("viewModel") final VirusAddBindingModel virusAddBindingModel,
                                final BindingResult bindingResult) {

        this.loadDataToViewModel(virusAddBindingModel);
        if (bindingResult.hasErrors()) {
            return super.view("/viruses/add", virusAddBindingModel);
        }

        if (!this.virusService.create(virusAddBindingModel)) {
            // TODO - proper error handling
            return super.view("/viruses/add", virusAddBindingModel);
        }

        return super.redirect("/viruses");
    }

    private VirusAddBindingModel loadDataToViewModel(final VirusAddBindingModel virusAddBindingModel) {
        virusAddBindingModel.setAllCapitals(this.capitalsService.getSimpleView());

        virusAddBindingModel.setAllMutations(
                Stream.of(Mutation.values())
                        .map(Enum::name)
                        .collect(Collectors.toUnmodifiableList()));

        virusAddBindingModel.setAllMagnitudes(
                Stream.of(Magnitude.values())
                        .map(Magnitude::getNormalizedName)
                        .collect(Collectors.toUnmodifiableList()));

        return virusAddBindingModel;
    }
}
