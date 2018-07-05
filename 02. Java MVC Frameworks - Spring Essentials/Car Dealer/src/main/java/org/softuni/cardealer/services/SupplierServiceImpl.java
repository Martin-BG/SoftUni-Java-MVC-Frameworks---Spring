package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(final SupplierRepository supplierRepository,
                               final ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }
}
