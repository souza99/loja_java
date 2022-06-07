package com.lojas.virtualStore.repository;

import com.lojas.virtualStore.domain.Cupom;
import com.lojas.virtualStore.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;

public interface CupomRepository extends JpaRepository<Cupom, Long> {

    @Query(value = "select a from Cupom a where a.nome like %?1%")
    Page<Cupom> findByNome(String nome, Pageable page);

    Page<Cupom> findAll(Pageable page);

    @Query(value = "select c from Cupom c where c.hash like %?1%")
    Page<Cupom> findByHash(String hash, Pageable page);

}
