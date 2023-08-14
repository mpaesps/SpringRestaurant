package br.com.guilherme.springrestaurant.services;

import br.com.guilherme.springrestaurant.entities.Customer;
import br.com.guilherme.springrestaurant.entities.Product;
import br.com.guilherme.springrestaurant.entities.dtos.CustomerDTO;
import br.com.guilherme.springrestaurant.entities.dtos.ProductDTO;
import br.com.guilherme.springrestaurant.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;


    public void saveCustomerToDatabase(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        repository.save(customer);

    }
    public void updateCustomerDatabase(Customer customer, CustomerDTO dto) {

        customer.setCustomerName(dto.getCustomerName());
        customer.setCustomerCPF(dto.getCustomerCPF());
        customer.setCustomerEmail(dto.getCustomerEmail());
        customer.setCustomerPhone(dto.getCustomerPhone());

        repository.save(customer);
    }
}

