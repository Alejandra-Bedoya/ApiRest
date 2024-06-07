package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.TouristPackage;

import java.util.List;
import java.util.Optional;

public interface TouristPackageDAO {

    Optional<TouristPackage>getById(Long packageId);

    List<TouristPackage>getAll();

    void save(TouristPackage touristPackage);

    void delete(Long packageId);

    void update(TouristPackage touristPackage);

}
