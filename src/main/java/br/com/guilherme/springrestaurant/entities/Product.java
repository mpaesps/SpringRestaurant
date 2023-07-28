package br.com.guilherme.springrestaurant.entities;

import br.com.guilherme.springrestaurant.entities.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private Integer productQuantity;

    public Product(ProductDTO dto) {
        this.setProductQuantity(dto.getProductQuantity());
        this.setProductName(dto.getProductName());
    }

    public Product() {
    }
}
