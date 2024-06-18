package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_name", nullable = false)
    private Customer customerName;


}


