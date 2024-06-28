package com.api.rest.apiRest.persistences.implementations;

import com.api.rest.apiRest.model.Employee;
import com.api.rest.apiRest.persistences.EmployeeDAO;
import com.api.rest.apiRest.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeImplementDAO implements EmployeeDAO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> getAll() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }
}
