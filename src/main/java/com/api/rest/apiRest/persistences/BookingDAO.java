package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.*;

import java.util.List;
import java.util.Optional;

public interface BookingDAO {

    Optional<Booking> getById(Long bookingId);

    List<Booking> getAll();

    void save(Booking booking);

    void delete(Long bookingId);

    void update(Booking booking);

    TouristPackage findTouristPackageById(Long fkPackageId);

    Payment findPaymentById(Long fkPaymentId);

    Employee findEmployeeById(Long fkEmployeeId);

    Customer findCustomerById(Long fkCustomerId);

    // Métodos para obtener entidades relacionadas
   // TouristPackage findTouristPackageById(Long fkPackageId);
    //Payment findPaymentById(Long fkPaymentId);
    //Employee findEmployeeById(Long fkEmployeeId);
    //Customer findCustomerById(Long fkCustomerId);

}
