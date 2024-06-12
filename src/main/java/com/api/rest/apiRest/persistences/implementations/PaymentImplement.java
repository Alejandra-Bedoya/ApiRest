package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.persistences.PaymentDAO;
import com.api.rest.apiRest.repository.TouristPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PaymentImplement implements PaymentDAO {
    @Autowired
    private TouristPackageRepository touristPackageRepository;


    @Override
    public Optional<TouristPackage> getById(Long paymentId) {
        return touristPackageRepository.findById(paymentId);
    }

    @Override
    public List<TouristPackage> getAll() {
        return (List<TouristPackage>) touristPackageRepository.findAll();
    }

    @Override
    public void save(TouristPackage touristPackage) {
        touristPackageRepository.save(touristPackage);
    }

    @Override
    public void delete(Long paymentId) {
        touristPackageRepository.deleteById(paymentId);

    }

    @Override
    public void update(TouristPackage touristPackage) {
        touristPackageRepository.save(touristPackage);
    }
}
