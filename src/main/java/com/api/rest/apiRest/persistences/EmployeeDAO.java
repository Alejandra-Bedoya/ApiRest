package com.api.rest.apiRest.persistences;

import com.api.rest.apiRest.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    Optional<Employee> getById(Long employeeId);

    List<Employee> getAll();

    void save(Employee employee);

    void delete(Long employeeId);

    void update(Employee employee);
}
