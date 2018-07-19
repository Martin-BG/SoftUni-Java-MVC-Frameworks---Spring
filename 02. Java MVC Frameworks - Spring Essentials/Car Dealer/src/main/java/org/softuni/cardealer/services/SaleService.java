package org.softuni.cardealer.services;

import java.util.List;

public interface SaleService {

    List<Double> getPurchasesForCustomer(final Long customerId);
}
