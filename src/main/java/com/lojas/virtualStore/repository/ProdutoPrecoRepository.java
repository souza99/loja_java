package com.lojas.virtualStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lojas.virtualStore.domain.ProdutoPreco;

public interface ProdutoPrecoRepository extends JpaRepository<ProdutoPreco, Long> {
	
	@Query(value = "SELECT MAX(ID) FROM ProdutoPreco")
	public ProdutoPreco ultimaEdicao();
	
	
	
}
