package com.softuni.residentevil.services;

import com.softuni.residentevil.repositories.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CapitalServiceImpl implements CapitalService {

    private final CapitalRepository capitalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(final CapitalRepository capitalRepository,
                              final ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }
}
