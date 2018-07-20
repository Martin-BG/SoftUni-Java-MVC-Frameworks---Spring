package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Sale;
import org.softuni.cardealer.domain.models.view.SaleViewModel;
import org.softuni.cardealer.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
                .map(p -> p.getCar().calculateTotalPrice() * p.evaluatePriceModifier())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<SaleViewModel> getAllSales() {
        final List<Sale> sales = this.saleRepository.findAll();
        final List<SaleViewModel> views = new ArrayList<>(sales.size());

        for (final Sale sale : sales) {
            final SaleViewModel model = this.modelMapper.map(sale, SaleViewModel.class);
            model.setCustomerName(sale.getCustomer().getName());
            model.setCustomerId(sale.getCustomer().getId());
            model.setCarId(sale.getCar().getId());
            model.setCarMake(sale.getCar().getMake());
            model.setCarModel(sale.getCar().getModel());
            model.setCarBasePrice(sale.getCar().calculateTotalPrice());
            model.setTotalDiscount(this.getTotalDiscount(sale));
            model.setCarFinalPrice(model.getCarBasePrice() * this.getPriceModifier(sale));
            views.add(model);
        }

        return views;
    }

    private double getPriceModifier(final Sale sale) {
        return 1.0 - (sale.getCustomer().discount() + sale.getDiscount());
    }

    private double getTotalDiscount(final Sale sale) {
        return (sale.getCustomer().discount() + sale.getDiscount()) * 100.0;
    }
}
