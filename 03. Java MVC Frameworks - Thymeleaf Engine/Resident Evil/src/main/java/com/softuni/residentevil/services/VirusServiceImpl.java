package com.softuni.residentevil.services;

import com.softuni.residentevil.etities.Virus;
import com.softuni.residentevil.repositories.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class VirusServiceImpl extends BaseService implements VirusService {

    private final VirusRepository virusRepository;

    @Autowired
    public VirusServiceImpl(final Validator validator,
                            final ModelMapper modelMapper,
                            final VirusRepository virusRepository) {
        super(validator, modelMapper);
        this.virusRepository = virusRepository;
    }

    @Override
    public boolean create(final Object dto) {
        return super.validateAndCreate(dto, Virus.class, this.virusRepository);
    }
}
