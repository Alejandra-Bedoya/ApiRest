package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.services.TouristPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/touristPackage")
public class TouristPackageController {

    @Autowired
    private TouristPackageService touristPackageService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllPackage () {

        List<TouristPackage> groupPackages = touristPackageService.getAll()
                .stream()
                .map(touristPackage -> TouristPackage.builder()
                        .packageId(touristPackage.getPackageId())
                        .packageDescription(touristPackage.getPackageDescription())
                        .packagePrice(touristPackage.getPackagePrice())
                        .packageCategory(touristPackage.getPackageCategory())
                        .destinationPlace(touristPackage.getDestinationPlace())
                        .departureDate(touristPackage.getDepartureDate())
                        .build()
                ).toList();

        return ResponseEntity.ok(groupPackages);
    }
}
