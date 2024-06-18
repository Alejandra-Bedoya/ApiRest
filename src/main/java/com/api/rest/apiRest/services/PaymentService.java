package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Optional<Payment> getById(Long paymentId);

    List<Payment> getAll();

    void save(Payment payment);

    void delete(Long paymentId);

    Payment update(Payment payment);
}
