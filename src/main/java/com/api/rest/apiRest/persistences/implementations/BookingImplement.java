package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.Booking;
import com.api.rest.apiRest.persistences.BookingDAO;
import com.api.rest.apiRest.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookingImplement implements BookingDAO {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Optional<Booking> getById(Long bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public List<Booking> getAll() {
        return (List<Booking>) bookingRepository.findAll();
    }

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void delete(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public void update(Booking booking) {
        bookingRepository.save(booking);
    }
}
