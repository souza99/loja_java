package com.lojas.virtualStore.controller;


import com.lojas.virtualStore.domain.ProdutoDomain;
import com.lojas.virtualStore.service.ProdutoService;
import com.lojas.virtualStore.service.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@RestController
@RequestMapping("/api")
@Tag(name="produto",description="API para crud de produto")
public class ProdutoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdutoService produtoService;
    
    
	
	@Operation(summary="Busca uma lista de produtos",description="Realiza a busca de todos os produtos, utilizando a paginação")
    @GetMapping(value = "/produtos", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProdutoDomain>> findAll(
    		@Parameter(description = "Nome do produto")
            @RequestBody(required = false) String nome, Pageable pageable){
        if(StringUtils.isEmpty(nome)){
            return ResponseEntity.ok(produtoService.findAll(pageable));
        }else {
            return ResponseEntity.ok(produtoService.findAllByNome(nome, pageable));
        }
    }
    
    
    

    @GetMapping(value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDomain> findProdutosById(@PathVariable long id){

        try{
        	ProdutoDomain produtoDomain = produtoService.findById(id);
        	return ResponseEntity.ok(produtoDomain);

        }catch(ResourceNotFoundException ex) {
        	logger.error(ex.getMessage());
        	
        	throw new ResponseStatusException(
        			HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

}
