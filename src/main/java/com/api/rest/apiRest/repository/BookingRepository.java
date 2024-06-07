package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}