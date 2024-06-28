package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.*;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<Booking> getById(Long bookingId);

    List<Booking> getAll();

    Booking save(Booking booking);

    void delete(Long bookingId);

    Booking update(Booking booking);

    TouristPackage findTouristPackageById(Long fkPackageId);

    Payment findPaymentById(Long fkPaymentId);

    Employee findEmployeeById(Long fkEmployeeId);

    Customer findCustomerById(Long fkCustomerId);

}
