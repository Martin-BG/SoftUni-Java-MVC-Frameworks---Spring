package com.softuni.residentevil.services;

import com.softuni.residentevil.etities.Capital;
import com.softuni.residentevil.repositories.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class CapitalServiceImpl extends BaseService implements CapitalService {

    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(final Validator validator,
                              final ModelMapper modelMapper,
                              final CapitalRepository capitalRepository) {
        super(validator, modelMapper);
        this.capitalRepository = capitalRepository;
    }


    @Override
    public boolean create(final Object dto) {
        return super.validateAndCreate(dto, Capital.class, this.capitalRepository);
    }
}
