package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.TouristPackage;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Optional<TouristPackage> getById(Long packageId);

    List<TouristPackage> getAll();

    void save(TouristPackage touristPackage);

    void delete(Long packageId);

    TouristPackage update(TouristPackage touristPackage);
}
