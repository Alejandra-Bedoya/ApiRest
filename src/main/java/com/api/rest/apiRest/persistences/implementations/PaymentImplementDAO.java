package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.Payment;
import com.api.rest.apiRest.persistences.PaymentDAO;
import com.api.rest.apiRest.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentImplementDAO implements PaymentDAO {
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Optional<Payment> getById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAll() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public void save(Payment payment) { paymentRepository.save(payment);
    }

    @Override
    public void delete(Long paymentId) { paymentRepository.deleteById(paymentId);

    }

    @Override
    public void update(Payment payment) { paymentRepository.save(payment);
    }

}
