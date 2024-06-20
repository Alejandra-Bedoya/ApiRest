package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.PaymentDTO;
import com.api.rest.apiRest.controllers.dto.TouristPackageDTO;
import com.api.rest.apiRest.model.Payment;
import com.api.rest.apiRest.model.TouristPackage;
import com.api.rest.apiRest.services.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllPayments() {
        List<PaymentDTO> payments = paymentService.getAll()
                .stream()
                .map(payment -> PaymentDTO.builder()
                        .paymentId(payment.getPaymentId())
                        .paymentAmount(payment.getPaymentAmount())
                        .paymentDate(payment.getPaymentDate())
                        .paymentMethod(payment.getPaymentMethod())
                        .build()
                ).toList();
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Payment> onePayment = paymentService.getById(id);

        if (onePayment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Payment payment = onePayment.get();
        PaymentDTO paymentDTO = PaymentDTO.builder()
                .paymentId(payment.getPaymentId())
                .build();

        return ResponseEntity.ok(paymentDTO);
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PaymentDTO paymentDTO) throws URISyntaxException {
        try {
            Payment payment = Payment.builder()
                    .paymentId(paymentDTO.getPaymentId())
                    .paymentAmount(paymentDTO.getPaymentAmount())
                    .paymentDate(paymentDTO.getPaymentDate())
                    .paymentMethod(paymentDTO.getPaymentMethod())
                    .build();

            paymentService.save(payment);
            PaymentDTO savedPaymentDTO = PaymentDTO.builder()
                    .paymentId(payment.getPaymentId())
                    .paymentAmount(payment.getPaymentAmount())
                    .paymentDate(payment.getPaymentDate())
                    .paymentMethod(payment.getPaymentMethod())
                    .build();

            return ResponseEntity.created(new URI("/payment/getById/" + payment.getPaymentId())).body(savedPaymentDTO);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving payment");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Payment payment) {
        try {

            Optional<Payment> validPayment = paymentService.getById(payment.getPaymentId());

            if (validPayment.isEmpty()) {
                System.out.println("El ID " + payment.getPaymentId() + " no existe");
                throw new EntityNotFoundException();
            }

            Payment updatedPayment = paymentService.update(payment);
            return  ResponseEntity.ok(updatedPayment);


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
            paymentService.delete(id);
            return ResponseEntity.ok("Payment deleted successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting payment");
        }
    }

}

