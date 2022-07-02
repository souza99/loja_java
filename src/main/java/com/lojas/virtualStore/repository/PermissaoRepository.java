package com.lojas.virtualStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lojas.virtualStore.domain.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	
	@Query(value = "from Permissao p where p.nome=?1")
	public List<Permissao> buscasPermissaoNome(String nome);
	
}
