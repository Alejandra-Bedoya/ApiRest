package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<Booking> getById(Long bookingId);

    List<Booking> getAll();

    void save(Booking booking);

    void delete(Long bookingId);

    Booking update(Booking booking);
}
