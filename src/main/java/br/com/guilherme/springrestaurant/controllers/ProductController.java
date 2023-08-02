package br.com.guilherme.springrestaurant.controllers;

import br.com.guilherme.springrestaurant.entities.Product;
import br.com.guilherme.springrestaurant.entities.ResponseMessage;
import br.com.guilherme.springrestaurant.entities.dtos.ProductDTO;
import br.com.guilherme.springrestaurant.repositories.ProductRepository;
import br.com.guilherme.springrestaurant.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductService service;

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {

        ResponseMessage responseMessage = new ResponseMessage();

        if (bindingResult.hasErrors()) {

            responseMessage.setStatusCode(400);
            responseMessage.setMessage("Error during object validation: " + productDTO);

            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

        service.saveProductToDatabase(productDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "name/{name}")
    public ResponseEntity<Object> returnProductByName(@PathVariable String name) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<Product> dbOject = repository.findByProductNameIgnoreCase(name);

        if (dbOject.isEmpty()) {
            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not found.");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        ProductDTO productDTO = new ProductDTO(dbOject.get());

        return new ResponseEntity<>(productDTO, HttpStatus.OK);

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> returnProductById(@PathVariable Long id) {

        ResponseMessage responseMessage = new ResponseMessage();
        Optional<Product> dbObject = repository.findById(id);

        if (dbObject.isEmpty()) {

            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not found");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dbObject.get(), HttpStatus.OK);
    }

    @GetMapping
    public List<Product> returnAllProducts() {
        return repository.findAll();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
        ResponseMessage responseMessage = new ResponseMessage();
        Optional<Product> dbOject = repository.findById(id);

        if (dbOject.isEmpty()) {

            responseMessage.setStatusCode(404);
            responseMessage.setMessage("Object not found.");

            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        }

        if (bindingResult.hasErrors()) {
            responseMessage.setStatusCode(400);
            responseMessage.setMessage("Error during object validation: " + productDTO);

            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

        service.updateProductDatabase(dbOject.get(), productDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
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
