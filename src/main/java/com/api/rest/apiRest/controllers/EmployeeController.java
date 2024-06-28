package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.EmployeeDTO;
import com.api.rest.apiRest.model.Employee;
import com.api.rest.apiRest.services.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAll()
                .stream()
                .map(employee -> EmployeeDTO.builder()
                        .employeeId(employee.getEmployeeId())
                        .employeeName(employee.getEmployeeName())
                        .employeePosition(employee.getEmployeePosition())
                        .employeePhone(employee.getEmployeePhone())
                        .employeeEmail(employee.getEmployeeEmail())
                        .employeeAddress(employee.getEmployeeAddress())
                        .build()
                ).toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Employee> oneEmployee = employeeService.getById(id);

        if (oneEmployee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Employee employee = oneEmployee.get();
        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .employeePosition(employee.getEmployeePosition())
                .employeePhone(employee.getEmployeePhone())
                .employeeEmail(employee.getEmployeeEmail())
                .employeeAddress(employee.getEmployeeAddress())
                .build();

        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EmployeeDTO employeeDTO) throws URISyntaxException {
        try {
            Employee employee = Employee.builder()

                    .employeeName(employeeDTO.getEmployeeName())
                    .employeePosition(employeeDTO.getEmployeePosition())
                    .employeePhone(employeeDTO.getEmployeePhone())
                    .employeeEmail(employeeDTO.getEmployeeEmail())
                    .employeeAddress(employeeDTO.getEmployeeAddress())
                    .build();

            employeeService.save(employee);

            EmployeeDTO savedEmployeeDTO = EmployeeDTO.builder()
                    .employeeId(employee.getEmployeeId())
                    .employeeName(employee.getEmployeeName())
                    .employeePosition(employee.getEmployeePosition())
                    .employeePhone(employee.getEmployeePhone())
                    .employeeEmail(employee.getEmployeeEmail())
                    .employeeAddress(employee.getEmployeeAddress())

                    .build();

            URI location = new URI("/employee/getById/" + savedEmployeeDTO.getEmployeeId());
            return ResponseEntity.created(location).body(savedEmployeeDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving employee");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO employeeDTO) {
        try {
            Optional<Employee> validEmployee = employeeService.getById(employeeDTO.getEmployeeId());

            if (validEmployee.isEmpty()) {
                throw new EntityNotFoundException();
            }

            Employee employee = Employee.builder()
                    .employeeId(employeeDTO.getEmployeeId())
                    .employeeName(employeeDTO.getEmployeeName())
                    .employeePosition(employeeDTO.getEmployeePosition())
                    .employeePhone(employeeDTO.getEmployeePhone())
                    .employeeEmail(employeeDTO.getEmployeeEmail())
                    .employeeAddress(employeeDTO.getEmployeeAddress())

                    .build();

            Employee updatedEmployee = employeeService.update(employee);
            return ResponseEntity.ok(updatedEmployee);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating employee");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            employeeService.delete(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting employee");
        }
    }
}
