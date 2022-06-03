package com.lojas.virtualStore.repository;


import com.lojas.virtualStore.domain.ProdutoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<ProdutoDomain, Long> {

    @Query(value = "select a from ProdutoDomain a where a.nome like %?1%")
    Page<ProdutoDomain> findByNome(String nome, Pageable page);

    Page<ProdutoDomain> findAll(Pageable page);

}
