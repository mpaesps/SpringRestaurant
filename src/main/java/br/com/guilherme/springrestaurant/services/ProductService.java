package br.com.guilherme.springrestaurant.services;

import br.com.guilherme.springrestaurant.entities.Product;
import br.com.guilherme.springrestaurant.entities.ResponseMessage;
import br.com.guilherme.springrestaurant.entities.dtos.ProductDTO;
import br.com.guilherme.springrestaurant.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    public void saveProductToDatabase(ProductDTO productDTO) {
        Product product = repository.findByProductNameIgnoreCase(productDTO.getProductName())
                .orElseGet(() -> new Product(productDTO));

        if (product.getId() != null) {
            product.setProductQuantity(product.getProductQuantity() + productDTO.getProductQuantity());
        }

        repository.save(product);
    }

    public void updateProductDatabase(Product product, ProductDTO dto) {

        product.setProductName(dto.getProductName());
        product.setProductQuantity(dto.getProductQuantity());

        repository.save(product);
    }
}

