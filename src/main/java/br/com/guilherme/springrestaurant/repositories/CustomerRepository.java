package br.com.guilherme.springrestaurant.repositories;

import br.com.guilherme.springrestaurant.entities.Customer;
import br.com.guilherme.springrestaurant.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //fazer consulta dinâmica
    @Query("SELECT c FROM Customer c WHERE LOWER(SUBSTRING(c.customerName, 1, 3)) = LOWER(:prefix)")
    Optional<Customer> findByCustomerNameStartingWithPrefix(@Param("prefix") String prefix);
}


