package com.api.rest.apiRest.controllers;

import com.api.rest.apiRest.controllers.dto.CustomerDTO;
import com.api.rest.apiRest.model.Customer;
import com.api.rest.apiRest.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAll")
    public ResponseEntity<?> findAllCustomers() {
        List<CustomerDTO> customers = customerService.getAll()
                .stream()
                .map(customer -> CustomerDTO.builder()
                        .customerId(customer.getCustomerId())
                        .customerName(customer.getCustomerName())
                        .customerEmail(customer.getCustomerEmail())
                        .customerPhone(customer.getCustomerPhone())
                        .customerAddress(customer.getCustomerAddress())
                        .build()
                ).toList();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Customer> oneCustomer = customerService.getById(id);

        if (oneCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = oneCustomer.get();
        CustomerDTO customerDTO = CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .customerEmail(customer.getCustomerEmail())
                .customerPhone(customer.getCustomerPhone())
                .customerAddress(customer.getCustomerAddress())
                .build();

        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody CustomerDTO customerDTO) {
        try {
            // Convertir DTO a entidad
            Customer customer = Customer.builder()
                    .customerName(customerDTO.getCustomerName())
                    .customerEmail(customerDTO.getCustomerEmail())
                    .customerPhone(customerDTO.getCustomerPhone())
                    .customerAddress(customerDTO.getCustomerAddress())
                    .build();

            // Guardar la entidad
            customerService.save(customer);

            // Convertir entidad guardada a DTO
            CustomerDTO savedCustomerDTO = CustomerDTO.builder()
                    .customerId(customer.getCustomerId())
                    .customerName(customer.getCustomerName())
                    .customerEmail(customer.getCustomerEmail())
                    .customerPhone(customer.getCustomerPhone())
                    .customerAddress(customer.getCustomerAddress())
                    .build();

            // Crear URI del nuevo recurso
            URI location = new URI("/customer/getById/" + savedCustomerDTO.getCustomerId());

            // Devolver respuesta con la URI y el DTO guardado
            return ResponseEntity.created(location).body(savedCustomerDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving customer");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CustomerDTO customerDTO) {
        try {
            // Buscar la entidad existente
            Optional<Customer> existingCustomer = customerService.getById(customerDTO.getCustomerId());

            if (existingCustomer.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
            }

            // Convertir DTO a entidad y actualizar
            Customer customer = existingCustomer.get();
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setCustomerEmail(customerDTO.getCustomerEmail());
            customer.setCustomerPhone(customerDTO.getCustomerPhone());
            customer.setCustomerAddress(customerDTO.getCustomerAddress());

            Customer updatedCustomer = customerService.update(customer);

            // Convertir entidad actualizada a DTO
            CustomerDTO updatedCustomerDTO = CustomerDTO.builder()
                    .customerId(updatedCustomer.getCustomerId())
                    .customerName(updatedCustomer.getCustomerName())
                    .customerEmail(updatedCustomer.getCustomerEmail())
                    .customerPhone(updatedCustomer.getCustomerPhone())
                    .customerAddress(updatedCustomer.getCustomerAddress())
                    .build();

            return ResponseEntity.ok(updatedCustomerDTO);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            customerService.delete(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer");
        }
    }
}
