package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingDAO {

    Optional<Booking> getById(Long bookingId);

    List<Booking> getAll();

    void save(Booking booking);

    void delete(Long bookingId);

    void update(Booking booking);
}
