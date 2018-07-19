package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Sale;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Double> getPurchasesForCustomer(final Long customerId) {
        final List<Sale> purchases = this.saleRepository
                .getPurchasesForCustomer(customerId);
        return purchases.stream()
                .map(p -> p.getCar().calculateTotalPrice() * p.getDiscount())
                .collect(Collectors.toUnmodifiableList());
    }
}
