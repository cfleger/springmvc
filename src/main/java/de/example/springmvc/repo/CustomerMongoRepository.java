package de.example.springmvc.repo;

import de.example.springmvc.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

    public List<Customer> findByLastName(String lastName);
}
