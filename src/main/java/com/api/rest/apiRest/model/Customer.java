package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameCustomer;
    private  Integer phoneCustomer;
    private String addressCustomer;
    private String emailCustomer;


    }
