package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.Customer;
import com.api.rest.apiRest.persistences.CustomerDAO;
import com.api.rest.apiRest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerImplementService implements CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Optional<Customer> getById(Long customerId) {
        return customerDAO.getById(customerId);
    }

    @Override
    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public void delete(Long customerId) {
        customerDAO.delete(customerId);
    }

    @Override
    public Customer update(Customer customer) {
        customerDAO.update(customer);
        return customer;
    }
}