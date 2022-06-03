package com.lojas.virtualStore.controller;


import com.lojas.virtualStore.domain.ProdutoDomain;
import com.lojas.virtualStore.service.ProdutoService;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.Getter;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@Tag(name="produto",description="API para crud de produto")
public class ProdutoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/produtos", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE);
    public ResponseEntity<Page<ProdutoDomain>> findAll(
            @RequestBody(required = false) String nome, Pageable pageable){
        if(StringUtils.isEmpty(nome)){
            return ResponseEntity.ok(produtoService.findAll(pageable));
        }else {
            return ResponseEntity.ok(produtoService.findAllByNome(nome, pageable));
        }
    }

    @GetMapping(value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDomain> findProdutosById(@PathVariable long id){

        /*try{

        }*/
    }

}
