package com.lojas.virtualStore.service;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String msg){
        super(msg);
    }

}
