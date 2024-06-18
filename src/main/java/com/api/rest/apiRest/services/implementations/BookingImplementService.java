package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.Booking;
import com.api.rest.apiRest.persistences.BookingDAO;
import com.api.rest.apiRest.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingImplementService implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Override
    public Optional<Booking> getById(Long bookingId) {
        return bookingDAO.getById(bookingId);
    }

    @Override
    public List<Booking> getAll() {
        return bookingDAO.getAll();
    }

    @Override
    public void save(Booking booking) {
        bookingDAO.save(booking);
    }

    @Override
    public void delete(Long bookingId) {
        bookingDAO.delete(bookingId);
    }

    @Override
    public Booking update(Booking booking) {
        bookingDAO.update(booking);
        return booking;
    }
}
