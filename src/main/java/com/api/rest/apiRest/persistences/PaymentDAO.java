package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.Payment;
import com.api.rest.apiRest.model.TouristPackage;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    Optional<Payment> getById(Long paymentId);

    List<Payment> getAll();

    void save(Payment payment);

    void delete(Long paymentId);

    void update(Payment payment);


}
