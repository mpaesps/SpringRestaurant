package br.com.guilherme.springrestaurant.entities.dtos;

import br.com.guilherme.springrestaurant.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    @NotNull(message = "Product name cant be null.")
    @NotBlank(message = "Product name cant be blank")
    private String productName;

    @NotNull(message = "Product quantity cant be null.")
    private Integer productQuantity;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        this.setProductQuantity(product.getProductQuantity());
        this.setProductName(product.getProductName());
    }
}
