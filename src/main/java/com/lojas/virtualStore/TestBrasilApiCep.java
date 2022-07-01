package com.lojas.virtualStore;

import org.springframework.web.client.RestTemplate;

import com.lojas.virtualStore.domain.CidadeDTO;
import com.lojas.virtualStore.domain.Endereco;
import com.lojas.virtualStore.domain.Usuario;

public class TestBrasilApiCep{

    public static void main(String[] args){
        String cep = "87703550";
        String url = "https://brasilapi.com.br/api/cep/v2/"+cep;
        RestTemplate restTemplate = new RestTemplate();
        Object ob = restTemplate.getForObject(url, Object.class);
        System.out.println(ob);
        
        CidadeDTO cidadeDTO = new CidadeDTO();
        CidadeDTO enderecoConvertido = cidadeDTO.converter(ob);
        
        
        
        Endereco endereco = new Endereco();
        
        endereco.setCidade(enderecoConvertido.getCity());
        endereco.setRua(enderecoConvertido.getStreet());
        endereco.setCep(enderecoConvertido.getCep());
        endereco.setEstado(enderecoConvertido.getState());
        
        
        System.out.println(endereco.getCidade());
        
    }
}

