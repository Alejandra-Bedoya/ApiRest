package com.api.rest.apiRest.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO {

    private Long employeeId;
    private String employeeName;
    private String employeePosition;
    private String employeeEmail;
    private String employeePhone;
    private String employeeAddress;

}