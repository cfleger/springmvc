package de.example.springmvc.controller;


import de.example.springmvc.domain.Customer;
import de.example.springmvc.repo.CustomerMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerMongoRepository customerMongoRepository;

    @GetMapping("/{id}")
    private ResponseEntity<Customer> read(@PathVariable final String id) {
        Optional<Customer> optionalCustomer = customerMongoRepository.findById(id);
        if(!optionalCustomer.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCustomer.get());
    }

    @GetMapping()
    private ResponseEntity<List<Customer>> byLastName(@RequestParam(required = false) final String lastName) {
        if (lastName == null) {
            return ResponseEntity.ok().body(customerMongoRepository.findAll());
        } else {
            return ResponseEntity.ok().body(customerMongoRepository.findByLastName(lastName));
        }
    }

    @PutMapping
    private ResponseEntity<Customer> create(@RequestBody final Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerMongoRepository.insert(customer));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable final String id) {
        customerMongoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
