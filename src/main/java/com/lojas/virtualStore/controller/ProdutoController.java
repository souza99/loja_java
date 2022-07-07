package com.lojas.virtualStore.controller;


import com.lojas.virtualStore.domain.Produto;
import com.lojas.virtualStore.service.BadResourceException;
import com.lojas.virtualStore.service.ProdutoService;
import com.lojas.virtualStore.service.ResourceAlreadyExistsException;
import com.lojas.virtualStore.service.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
@Tag(name="produto",description="API para crud de produto")
public class ProdutoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProdutoService produtoService;
    
    
	
	@Operation(summary="Busca uma lista de produtos",description="Realiza a busca de todos os produtos, utilizando a paginação")
    @GetMapping(value = "/produtos", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Produto>> findAll(
    		@Parameter(description = "Nome do produto")
            @RequestBody(required = false) String nome, Pageable pageable){
        if(StringUtils.isEmpty(nome)){
            return ResponseEntity.ok(produtoService.findAll(pageable));
        }else {
            return ResponseEntity.ok(produtoService.findAllByNome(nome, pageable));
        }
    }
    
    
    

    @GetMapping(value = "/produto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> findProdutosById(@PathVariable long id){

        try{
        	Produto produto = produtoService.findById(id);
        	return ResponseEntity.ok(produto);

        }catch(ResourceNotFoundException ex) {
        	logger.error(ex.getMessage());
        	
        	throw new ResponseStatusException(
        			HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/produto")
    public  ResponseEntity<Produto> addProduto(@RequestBody Produto produto)
        throws URISyntaxException{

        try{
            Produto novoProduto = produtoService.save(produto);
            return ResponseEntity.created(new URI("/api/produto/" + novoProduto.getId())).body(produto);

        }catch (ResourceAlreadyExistsException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (BadResourceException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(value = "/produto/{id}")
    public ResponseEntity<Produto> updateProduto(@Valid @RequestBody Produto produto,
                                                 @PathVariable long id){
        try{
            produto.setId(id);
            produtoService.update(produto);
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }catch (BadResourceException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @DeleteMapping(value = "/produto/{id}")
    public ResponseEntity<Void> deleProdutoById(@PathVariable long id){

        try {
            produtoService.deletedById(id);
            return ResponseEntity.ok().build();
        }catch(ResourceNotFoundException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }

    }
    
    @GetMapping(path="/buscaProdutoPorCategoria")
    //localhost:8080/api/produto/atualizarValorCategoria?percentual=5&idCategoria=1&tipoOperacao=desconto
    public ResponseEntity<Void> buscaProdutoPorCategoria(@RequestParam Double percentual, @RequestParam Long idCategoria, @RequestParam String tipoOperacao){
    	produtoService.atualizarValorProdutoCategoria(idCategoria, percentual, tipoOperacao);
    	return ResponseEntity.ok().build();
    }


}
