package com.lojas.virtualStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lojas.virtualStore.domain.Produto;
import com.lojas.virtualStore.domain.ProdutoPreco;
import com.lojas.virtualStore.repository.ProdutoPrecoRepository;

@Service
public class ProdutoPrecoService {
	
	@Autowired
	private ProdutoPrecoRepository produtoPrecoRepository;
	
	
	public ProdutoPreco save(ProdutoPreco produtoPreco) {
		return produtoPreco;
	}
	
	public Boolean validaAltercaoPreco(Produto produto) {
		
		return null;
	}

}
