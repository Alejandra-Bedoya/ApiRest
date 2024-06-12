package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.persistences.TouristPackageDAO;
import com.api.rest.apiRest.services.TouristPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristPackageImplementService implements TouristPackageService {

    @Autowired
    private TouristPackageDAO touristPackageDAO;

    @Override
    public Optional<TouristPackage> getById(Long packageId) {
        return touristPackageDAO.getById(packageId);
    }

    @Override
    public List<TouristPackage> getAll() {
        return touristPackageDAO.getAll();
    }

    @Override
    public void save(TouristPackage touristPackage) {
        touristPackageDAO.save(touristPackage);
    }

    @Override
    public void delete(Long packageId) {
        touristPackageDAO.delete(packageId);
    }

    @Override
    public TouristPackage update(TouristPackage touristPackage) {
        touristPackageDAO.update(touristPackage);
        return touristPackage;
    }
}
