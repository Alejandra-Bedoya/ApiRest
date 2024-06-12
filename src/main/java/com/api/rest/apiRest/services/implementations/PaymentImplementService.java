package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.persistences.PaymentDAO;
import com.api.rest.apiRest.persistences.TouristPackageDAO;
import com.api.rest.apiRest.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PaymentImplementService implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public Optional<TouristPackage> getById(Long paymentId) {
        return paymentDAO.getById(paymentId);
    }

    @Override
    public List<TouristPackage> getAll() {
        return paymentDAO.getAll();
    }

    @Override
    public void save(TouristPackage touristPackage) { paymentDAO.save(touristPackage); }

    @Override
    public void delete(Long paymentId) { paymentDAO.delete(paymentId); }

    @Override
    public TouristPackage update(TouristPackage touristPackage) {
        paymentDAO.update(touristPackage);
        return touristPackage;
    }
}
