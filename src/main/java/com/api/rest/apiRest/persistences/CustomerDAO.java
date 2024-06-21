package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    Optional<Customer> getById(Long customerId);

    List<Customer> getAll();

    void save(Customer customer);

    void delete(Long customerId);

    void update(Customer customer);
}