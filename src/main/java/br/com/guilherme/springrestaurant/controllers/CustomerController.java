package br.com.guilherme.springrestaurant.controllers;

import br.com.guilherme.springrestaurant.entities.ResponseMessage;
import br.com.guilherme.springrestaurant.entities.dtos.CustomerDTO;
import br.com.guilherme.springrestaurant.repositories.CustomerRepository;
import br.com.guilherme.springrestaurant.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerService service;

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody @Valid CustomerDTO customerDTO, BindingResult bindingResult){
        ResponseMessage responseMessage = new ResponseMessage();

        if (bindingResult.hasErrors()){
            responseMessage.setStatusCode(400);
            responseMessage.setMessage("Error during Object validation" + customerDTO);

            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

         service.saveCustomerToDatabase(customerDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
        }


    }



