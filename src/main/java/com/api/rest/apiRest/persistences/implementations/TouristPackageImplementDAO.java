package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.persistences.TouristPackageDAO;
import com.api.rest.apiRest.repository.TouristPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TouristPackageImplementDAO implements TouristPackageDAO {

    @Autowired
    private TouristPackageRepository touristPackageRepository;

    @Override
    public Optional<TouristPackage> getById(Long packageId) {
        return touristPackageRepository.findById(packageId);
    }

    @Override
    public List<TouristPackage> getAll() {
        return (List<TouristPackage>) touristPackageRepository.findAll();
    }

    @Override
    public void save(TouristPackage touristPackage) { touristPackageRepository.save(touristPackage);
    }

    @Override
    public void delete(Long packageId) { touristPackageRepository.deleteById(packageId);
    }

    @Override
    public void update(TouristPackage touristPackage) { touristPackageRepository.save(touristPackage);
    }
}
