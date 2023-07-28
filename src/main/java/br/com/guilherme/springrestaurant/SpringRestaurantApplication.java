package br.com.guilherme.springrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestaurantApplication {

    public static void main(String[] args) {

	/*
    It's a restaurant system where it will be possible to:
    - Maintain the store's inventory
    - Have a customer registry
    - Have a reservations registry
    - Send SMS to customers
    The system requires:
    - Authentication
    - Support for high access capacities
 */

        SpringApplication.run(SpringRestaurantApplication.class, args);
    }

}
