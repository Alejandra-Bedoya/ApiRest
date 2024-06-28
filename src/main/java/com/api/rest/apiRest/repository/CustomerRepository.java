package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}