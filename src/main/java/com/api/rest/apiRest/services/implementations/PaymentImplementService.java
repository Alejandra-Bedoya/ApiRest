package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.Payment;
import com.api.rest.apiRest.persistences.PaymentDAO;
import com.api.rest.apiRest.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentImplementService implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public Optional<Payment> getById(Long paymentId) {
        return paymentDAO.getById(paymentId);
    }

    @Override
    public List<Payment> getAll() {
        return paymentDAO.getAll();
    }

    @Override
    public void save(Payment payment) { paymentDAO.save(payment); }

    @Override
    public void delete(Long paymentId) { paymentDAO.delete(paymentId); }

    @Override
    public Payment update(Payment payment) {
        paymentDAO.update(payment);
        return payment;
    }


}



