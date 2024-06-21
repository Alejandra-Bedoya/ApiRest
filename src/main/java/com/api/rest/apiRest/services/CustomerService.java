package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getById(Long customerId);

    List<Customer> getAll();

    void save(Customer customer);

    void delete(Long customerId);

    Customer update(Customer customer);
}
