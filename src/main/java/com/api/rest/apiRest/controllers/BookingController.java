package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.BookingDTO;
import com.api.rest.apiRest.model.Booking;
import com.api.rest.apiRest.model.Payment;
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
                        .fk_package_id(booking.getTouristPackage().getPackageId())
                        .fk_payment_id(booking.getPayment().getPaymentId())
                        .fk_employee_id(booking.getEmployee().getEmployeeId())
                        .fk_customer_id(booking.getCustomer().getCustomerId())
                        .customerName(booking.getCustomer().getCustomerName())
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
                .build();

        return ResponseEntity.ok(bookingDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Booking booking) throws URISyntaxException {
        bookingService.save(booking);
        return ResponseEntity.created(new URI("/booking/getById/" + booking.getBookingId())).build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Booking booking) {
        try {

            Optional<Booking> validBooking = bookingService.getById(booking.getBookingId());

            if (validBooking.isEmpty()) {
                System.out.println("El ID " + booking.getBookingId() + " no existe");
                throw new EntityNotFoundException();
            }

            Booking updatedBooking = bookingService.update(booking);
            return  ResponseEntity.ok(updatedBooking);


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
            bookingService.delete(id);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking");
        }
    }
}
