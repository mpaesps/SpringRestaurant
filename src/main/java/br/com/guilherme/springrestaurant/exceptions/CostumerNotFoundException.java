package br.com.guilherme.springrestaurant.exceptions;

public class CostumerNotFoundException extends RuntimeException{
    public CostumerNotFoundException(Long id) {
        super(String.format("User with id %d not found.",id));
    }
    public CostumerNotFoundException(String name) {
        super(String.format("User %s not found.",name));
    }

}
