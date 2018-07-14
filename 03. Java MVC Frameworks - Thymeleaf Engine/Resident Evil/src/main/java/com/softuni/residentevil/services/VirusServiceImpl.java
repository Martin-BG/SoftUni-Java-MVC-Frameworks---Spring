package com.softuni.residentevil.services;

import com.softuni.residentevil.etities.Virus;
import com.softuni.residentevil.models.binding.VirusAddBindingModel;
import com.softuni.residentevil.repositories.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl extends BaseService implements VirusService {

    private final VirusRepository virusRepository;
    private final CapitalService capitalService;

    @Autowired
    public VirusServiceImpl(final Validator validator,
                            final ModelMapper modelMapper,
                            final VirusRepository virusRepository,
                            final CapitalService capitalService) {
        super(validator, modelMapper);
        this.virusRepository = virusRepository;
        this.capitalService = capitalService;
    }

    @Override
    public boolean create(final Object dto) {

        if (!super.isValid(dto)) {
            return false; // TODO - proper error handling
        }

        final VirusAddBindingModel virusDto = (VirusAddBindingModel) dto;
        final Virus virus = super.map(dto, Virus.class);

        virus.setCapitals(virusDto
                .getCapitals()
                .stream()
                .map(this.capitalService::getById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));

        try {
            this.virusRepository.saveAndFlush(virus);
            return true;
        } catch (Throwable e) {
            return false;   // TODO - proper error handling
        }
    }
}
