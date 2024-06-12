package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", nullable = false)
    private Long bookingId;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate; // YYYY-MM-DD

    //@Column(name = "booking_payment", nullable = false)
    //private Double bookingPayment;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY )
    @JoinColumn(name = "fk_package_id", nullable = false)
    private TouristPackage touristPackage;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_payment_id", nullable = false)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_customer_id", nullable = false)
    private Customer customer;



}


