package com.softuni.residentevil.services;

import com.softuni.residentevil.repositories.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VirusServiceImpl implements VirusService {

    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(final VirusRepository virusRepository,
                            final ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }
}
