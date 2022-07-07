package com.lojas.virtualStore.repository;


import com.lojas.virtualStore.domain.Produto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query(value = "select a from produto a where a.nome like %?1%")
    Page<Produto> findByNome(String nome, Pageable page);

    Page<Produto> findAll(Pageable page);    
    
    
    @Query(value = "select p from produto p where p.categoria.id=?1")
    public List<Produto> buscaProdutoPorCategoria(Long idCategoria);

}
