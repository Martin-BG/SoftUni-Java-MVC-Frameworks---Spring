package org.softuni.cardealer.services;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.view.CustomerDetailsView;
import org.softuni.cardealer.domain.models.view.CustomerViewModel;
import org.softuni.cardealer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleService saleService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
                               final SaleService saleService,
                               final ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.saleService = saleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerViewModel> getByBirthDate(final Boolean isOrderDescending) {
        return isOrderDescending
                ? this.customerRepository.getByBirthDateDesc()
                : this.customerRepository.getByBirthDateAsc();
    }

    @Override
    public CustomerDetailsView getDetailsById(final Long customerId) {
        final Customer customer = this.customerRepository
                .findById(customerId)
                .orElse(null);

        if (customer == null) {
            return null;
        }

        final CustomerDetailsView model = this.modelMapper.map(customer, CustomerDetailsView.class);

        final List<Double> purchases = this.saleService.getPurchasesForCustomer(customerId);

        model.setCarsBought(purchases.size());
        model.setMoneySpent(purchases.stream().reduce(0.0d, Double::sum));

        if (customer.getYoungDriver()) {
            model.setMoneySpent(model.getMoneySpent() * 0.95d);
        }

        return model;
    }
}
