package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.Customer;
import com.api.rest.apiRest.persistences.CustomerDAO;
import com.api.rest.apiRest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerImplementDAO implements CustomerDAO {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public List<Customer> getAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }
}
