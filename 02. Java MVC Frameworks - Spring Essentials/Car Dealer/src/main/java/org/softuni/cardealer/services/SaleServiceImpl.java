package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(final SaleRepository saleRepository,
                           final ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
    }
}
