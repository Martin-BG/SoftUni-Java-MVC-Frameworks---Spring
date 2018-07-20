package org.softuni.cardealer.services;

import org.softuni.cardealer.domain.models.view.SaleViewModel;

import java.util.List;

public interface SaleService {

    List<Double> getPurchasesForCustomer(final Long customerId);

    List<SaleViewModel> getAllSales();
}
