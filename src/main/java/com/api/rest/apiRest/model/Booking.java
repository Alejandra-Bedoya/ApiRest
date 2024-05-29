package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "booking_date", nullable = false)
    private Date bookingDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "departure_date", nullable = false)
    private Date departureDate; // Fecha de salida del viaje

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date", nullable = false)
    private Date returnDate; // Fecha de regreso del viaje

    private Integer quantity;
    private Double total;

    @Column(name = "reservation_payment")
    private Double reservationPayment;
    //private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private TouristPackage touristPackage;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}