package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "employee_email", nullable = false)
    private String employeeEmail;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_phone", nullable = false)
    private String employeePhone;

    @Column(name = "employee_address", nullable = false)
    private String employeeAddress;

    @Column(name = "employee_position", nullable = false)
    private String employeePosition;

}
