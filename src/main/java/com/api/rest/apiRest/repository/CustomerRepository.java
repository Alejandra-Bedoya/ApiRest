package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}