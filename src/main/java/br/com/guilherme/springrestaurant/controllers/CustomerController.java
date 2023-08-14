package br.com.guilherme.springrestaurant.controllers;

import br.com.guilherme.springrestaurant.entities.Customer;
import br.com.guilherme.springrestaurant.entities.ResponseMessage;
import br.com.guilherme.springrestaurant.entities.dtos.CustomerDTO;
import br.com.guilherme.springrestaurant.entities.dtos.ProductDTO;
import br.com.guilherme.springrestaurant.repositories.CustomerRepository;
import br.com.guilherme.springrestaurant.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (bindingResult.hasErrors()) {
            responseMessage.setStatusCode(400);
            responseMessage.setMessage("Error during Object validation" + customerDTO);

            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

        service.saveCustomerToDatabase(customerDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity<Object> findCustomerByName(@PathVariable String name) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<Customer> dbObject = repository.findByCustomerNameIgnoreCaseContaining(name);

        if (dbObject.isEmpty()) {
            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not found.");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        List<CustomerDTO> customerDTO = dbObject.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());


        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> findCustomerById(@PathVariable Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<Customer> dbObject = repository.findById(id);

        if (dbObject.isEmpty()) {
            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not found");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dbObject.get(), HttpStatus.OK);
    }

    @GetMapping
    public List<Customer> returnAllCustomers() {
        return repository.findAll();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        Optional<Customer> dbObject = repository.findById(id);

        if (dbObject.isEmpty()) {
            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not Found");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()) {
            responseMessage.setStatusCode(400);
            responseMessage.setMessage("Error during Object validation" + customerDTO);

            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

        service.updateCustomerDatabase(dbObject.get(), customerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (repository.existsById(id)) {

            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        responseMessage.setStatusCode(400);
        responseMessage.setMessage("Object not found.");
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
}



