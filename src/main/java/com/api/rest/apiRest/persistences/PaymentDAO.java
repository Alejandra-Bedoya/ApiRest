package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.TouristPackage;

import java.util.List;
import java.util.Optional;

public interface PaymentDAO {

    Optional<TouristPackage> getById(Long paymentId);

    List<TouristPackage> getAll();

    void save(TouristPackage touristPackage);

    void delete(Long packageId);

    void update(TouristPackage touristPackage);

}
