package com.api.rest.apiRest.repository;

import com.api.rest.apiRest.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}