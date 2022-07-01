package com.lojas.virtualStore.domain;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;

import com.lojas.virtualStore.DTO.UsuarioDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {
    String cep;
    String city;
    String state;
    String street;
    
    //CONVERTE JSON TO ENDEREÇO PARA SALVAR NO BANCO
    public CidadeDTO converter(Object ob){
    	CidadeDTO enderecoDTO = new CidadeDTO();
		BeanUtils.copyProperties(ob, enderecoDTO);
		return this;
	}
    
}
