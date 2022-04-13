package com.example.webflux.Service;

public class WebClientService extends RuntimeException{

    private int statusCode;

    public WebClientService(String message, int statusCode){
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }

}
