package com.api.rest.apiRest.services.implementations;

import com.api.rest.apiRest.model.Employee;
import com.api.rest.apiRest.persistences.EmployeeDAO;
import com.api.rest.apiRest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImplementService implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    public Optional<Employee> getById(Long employeeId) {
        return employeeDAO.getById(employeeId);
    }

    @Override
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void delete(Long employeeId) {
        employeeDAO.delete(employeeId);
    }

    @Override
    public Employee update(Employee employee) {
        employeeDAO.update(employee);
        return employee;
    }
}
