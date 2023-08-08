package br.com.guilherme.springrestaurant.repositories;

import br.com.guilherme.springrestaurant.entities.Customer;
import br.com.guilherme.springrestaurant.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerNameIgnoreCaseContaining(String customerName);
}
