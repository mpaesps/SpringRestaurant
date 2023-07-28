package br.com.guilherme.springrestaurant.entities;

import lombok.Data;

@Data
public class ResponseMessage {

    private int statusCode;

    private String message;

}
