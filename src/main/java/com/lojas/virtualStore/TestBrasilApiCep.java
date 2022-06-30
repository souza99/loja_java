package com.lojas.virtualStore;

import org.springframework.web.client.RestTemplate;

public class TestBrasilApiCep{

    public static void main(String[] args){
        String cep = "87703550";
        String url = "https://brasilapi.com.br/api/cep/v2/"+cep;
        RestTemplate restTemplate = new RestTemplate();
        Object ob = restTemplate.getForObject(url, Object.class);
        System.out.println(ob);
    }
}

