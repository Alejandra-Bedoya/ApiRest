package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.TouristPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristPackageRepository extends CrudRepository<TouristPackage, Long> {
}