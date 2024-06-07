package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}