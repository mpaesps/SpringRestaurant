package br.com.guilherme.springrestaurant.entities;

import br.com.guilherme.springrestaurant.entities.dtos.CustomerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "customer")
public class Customer  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerCPF;
    public Customer(CustomerDTO dto) {
        this.setCustomerName(dto.getCustomerName());
        this.setCustomerEmail(dto.getCustomerEmail());
        this.setCustomerPhone(dto.getCustomerPhone());
        this.setCustomerCPF(dto.getCustomerCPF());
    }
    public Customer() {
    }


}
