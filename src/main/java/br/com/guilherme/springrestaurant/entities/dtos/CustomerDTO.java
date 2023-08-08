package br.com.guilherme.springrestaurant.entities.dtos;


import br.com.guilherme.springrestaurant.entities.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDTO implements Serializable {
    @NotNull(message = "Product name can not be null.")
    private String customerName;
    @NotBlank
    @Email
    private String customerEmail;

    @NotBlank
    @Pattern(regexp = "\\d{9}", message = "phone number is not valid")
    private String customerPhone;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "CPF is not valid")
    private String customerCPF;

    public CustomerDTO(Customer customer) {
        this.setCustomerName(customer.getCustomerName());
        this.setCustomerEmail(customer.getCustomerEmail());
        this.setCustomerPhone(customer.getCustomerPhone());
        this.setCustomerCPF(customer.getCustomerCPF());
    }

    public CustomerDTO() {
    }
}
