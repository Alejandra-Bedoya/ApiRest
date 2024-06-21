package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.*;
import com.api.rest.apiRest.persistences.BookingDAO;
import com.api.rest.apiRest.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookingImplementDAO implements BookingDAO {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TouristPackageRepository touristPackageRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

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

    @Override
    public TouristPackage findTouristPackageById(Long fkPackageId) {
        return touristPackageRepository.findById(fkPackageId)
                .orElseThrow(() -> new EntityNotFoundException("TouristPackage not found"));
    }

    @Override
    public Payment findPaymentById(Long fkPaymentId) {
        return paymentRepository.findById(fkPaymentId)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found"));
    }

    @Override
    public Employee findEmployeeById(Long fkEmployeeId) {
        return employeeRepository.findById(fkEmployeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public Customer findCustomerById(Long fkCustomerId) {
        return customerRepository.findById(fkCustomerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}

