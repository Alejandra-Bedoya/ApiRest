package com.api.rest.apiRest.services;

import com.api.rest.apiRest.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Optional<Employee> getById(Long employeeId);

    List<Employee> getAll();

    void save(Employee employee);

    void delete(Long employeeId);

    Employee update(Employee employee);
}
