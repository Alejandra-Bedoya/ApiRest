package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.BookingDTO;
import com.api.rest.apiRest.model.*;
import com.api.rest.apiRest.services.BookingService;
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

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllBookings() {
        List<BookingDTO> bookings = bookingService.getAll()
                .stream()
                .map(booking -> BookingDTO.builder()

                        .bookingId(booking.getBookingId())
                        .bookingDate(booking.getBookingDate())
                        .fkPackageId(booking.getTouristPackage().getPackageId())
                        .fkPaymentId(booking.getPayment().getPaymentId())
                        .fkEmployeeId(booking.getEmployee().getEmployeeId())
                        .fkCustomerId(booking.getCustomer().getCustomerId())
                        .customerName(booking.getCustomerName())
                        .destinationPlace(booking.getDestinationPlace())
                        .build()
                ).toList();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Booking> oneBooking = bookingService.getById(id);

        if (oneBooking.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = oneBooking.get();
        BookingDTO bookingDTO = BookingDTO.builder()
                .bookingId(booking.getBookingId())
                .bookingDate(booking.getBookingDate())
                .fkPackageId(booking.getTouristPackage().getPackageId())
                .fkPaymentId(booking.getPayment().getPaymentId())
                .fkEmployeeId(booking.getEmployee().getEmployeeId())
                .fkCustomerId(booking.getCustomer().getCustomerId())
                .customerName(booking.getCustomerName())
                .destinationPlace(booking.getDestinationPlace())
                .build();

        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BookingDTO bookingDTO) throws URISyntaxException {
        try {
            Booking booking = new Booking();
            booking.setBookingDate(bookingDTO.getBookingDate());

            // Loading related entities
            booking.setTouristPackage(bookingService.findTouristPackageById(bookingDTO.getFkPackageId()));
            booking.setPayment(bookingService.findPaymentById(bookingDTO.getFkPaymentId()));
            booking.setEmployee(bookingService.findEmployeeById(bookingDTO.getFkEmployeeId()));
            booking.setCustomer(bookingService.findCustomerById(bookingDTO.getFkCustomerId()));

            Booking savedBooking = bookingService.save(booking);
            return ResponseEntity.created(new URI("/booking/getById/" + savedBooking.getBookingId())).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Related entity not found");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving booking");
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BookingDTO bookingDTO) {
        try {
            Optional<Booking> validBooking = bookingService.getById(bookingDTO.getBookingId());

            if (validBooking.isEmpty()) {
                System.out.println("El ID " + bookingDTO.getBookingId() + " no existe");
                throw new EntityNotFoundException();
            }

            Booking booking = validBooking.get();
            booking.setBookingDate(bookingDTO.getBookingDate());

            // Loading related entities
            booking.setTouristPackage(bookingService.findTouristPackageById(bookingDTO.getFkPackageId()));
            booking.setPayment(bookingService.findPaymentById(bookingDTO.getFkPaymentId()));
            booking.setEmployee(bookingService.findEmployeeById(bookingDTO.getFkEmployeeId()));
            booking.setCustomer(bookingService.findCustomerById(bookingDTO.getFkCustomerId()));

            Booking updatedBooking = bookingService.update(booking);

            BookingDTO updatedBookingDTO = BookingDTO.builder()
                    .bookingId(updatedBooking.getBookingId())
                    .bookingDate(updatedBooking.getBookingDate())
                    .fkPackageId(updatedBooking.getTouristPackage().getPackageId())
                    .fkPaymentId(updatedBooking.getPayment().getPaymentId())
                    .fkEmployeeId(updatedBooking.getEmployee().getEmployeeId())
                    .fkCustomerId(updatedBooking.getCustomer().getCustomerId())
                    .customerName(updatedBooking.getCustomerName())
                    .destinationPlace(updatedBooking.getDestinationPlace())
                    .build();

            return ResponseEntity.ok(updatedBookingDTO);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        } catch (DataIntegrityViolationException e) {
            System.out.println("Dentro del error" + bookingDTO.getBookingDate());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating booking");
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            bookingService.delete(id);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking");
        }
    }
}
