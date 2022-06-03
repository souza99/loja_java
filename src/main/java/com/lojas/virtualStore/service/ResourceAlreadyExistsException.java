package com.lojas.virtualStore.service;

public class ResourceAlreadyExistsException  extends Exception{

    public ResourceAlreadyExistsException(){

    }

    public ResourceAlreadyExistsException(String smg){
        super(smg);
    }
}
