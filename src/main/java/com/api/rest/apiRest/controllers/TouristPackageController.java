package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.TouristPackageDTO;
import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.services.TouristPackageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/touristPackage")
public class TouristPackageController {

    @Autowired
    private TouristPackageService touristPackageService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllPackage() {

        List<TouristPackageDTO> Packages = touristPackageService.getAll()
                .stream()
                .map(touristPackage -> TouristPackageDTO.builder()
                        .packageId(touristPackage.getPackageId())
                        .packageCategory(touristPackage.getPackageCategory())
                        .destinationPlace(touristPackage.getDestinationPlace())
                        .packageDescription(touristPackage.getPackageDescription())
                        .departureDate(touristPackage.getDepartureDate())
                        .returnDate(touristPackage.getReturnDate())
                        .durationDays(touristPackage.getDurationDays())
                        .packagePrice(touristPackage.getPackagePrice())
                        .availableSpots(touristPackage.getAvailableSpots())
                        .build()
                ).toList();
        return ResponseEntity.ok(Packages);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Optional<TouristPackage> onePackage = touristPackageService.getById(id);

        if (onePackage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TouristPackage touristPackage = onePackage.get();
        TouristPackageDTO touristPackageDTO = TouristPackageDTO.builder()
                .packageId(touristPackage.getPackageId())
                .destinationPlace(touristPackage.getDestinationPlace())
                .build();

        return ResponseEntity.ok(touristPackageDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TouristPackageDTO touristPackageDTO) {
        try {
            // Convertir DTO a entidad
            TouristPackage touristPackage = TouristPackage.builder()
                    .packageCategory(touristPackageDTO.getPackageCategory())
                    .destinationPlace(touristPackageDTO.getDestinationPlace())
                    .packageDescription(touristPackageDTO.getPackageDescription())
                    .departureDate(touristPackageDTO.getDepartureDate())
                    .returnDate(touristPackageDTO.getReturnDate())
                    .durationDays(touristPackageDTO.getDurationDays())
                    .packagePrice(touristPackageDTO.getPackagePrice())
                    .availableSpots(touristPackageDTO.getAvailableSpots())
                    .build();

            // Guardar la entidad
            touristPackageService.save(touristPackage);

            // Convertir entidad guardada a DTO
            TouristPackageDTO savedTouristPackageDTO = TouristPackageDTO.builder()
                    .packageId(touristPackage.getPackageId())
                    .packageCategory(touristPackage.getPackageCategory())
                    .destinationPlace(touristPackage.getDestinationPlace())
                    .packageDescription(touristPackage.getPackageDescription())
                    .departureDate(touristPackage.getDepartureDate())
                    .returnDate(touristPackage.getReturnDate())
                    .durationDays(touristPackage.getDurationDays())
                    .packagePrice(touristPackage.getPackagePrice())
                    .availableSpots(touristPackage.getAvailableSpots())
                    .build();

            // Crear URI del nuevo recurso
            URI location = new URI("/touristPackage/getById/" + savedTouristPackageDTO.getPackageId());

            // Devolver respuesta con la URI y el DTO guardado
            return ResponseEntity.created(location).body(savedTouristPackageDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving tourist package");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody TouristPackage touristPackage) {
        try {

            Optional<TouristPackage> validTP = touristPackageService.getById(touristPackage.getPackageId());

            if (validTP.isEmpty()) {
                System.out.println("El ID: " + touristPackage.getPackageId() + " no existe");
                throw new EntityNotFoundException();
            }

            TouristPackage updatedPackage = touristPackageService.update(touristPackage);
            return ResponseEntity.ok(updatedPackage);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Package not found");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating package");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            touristPackageService.delete(id);
            return ResponseEntity.ok("Package deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting package");
        }
    }

}




