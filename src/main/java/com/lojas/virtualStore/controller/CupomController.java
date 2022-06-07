package com.lojas.virtualStore.controller;

import com.lojas.virtualStore.domain.Cupom;
import com.lojas.virtualStore.domain.Produto;
import com.lojas.virtualStore.service.CupomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-cupom")
@Tag(name="cupom",description="API para crud de cupom")
public class CupomController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CupomService cupomService;


    @Operation(summary="Busca uma lista de cupons",description="Realiza a busca de cupons por nome e paginação" +
            " ou por hash e paginação " +
            "ou somento por paginação")
    @GetMapping(value = "/cupons", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Cupom>> findAll(
            @Parameter(description = "Valor para filtrar")
            @RequestBody(required = false) String nome, String hash, Pageable pageable){
        if(StringUtils.isEmpty(nome) && !StringUtils.isEmpty(hash)){
            return ResponseEntity.ok(cupomService.findAllByHash(hash, pageable));
        }if(!StringUtils.isEmpty(nome) && StringUtils.isEmpty(hash)){
            return ResponseEntity.ok(cupomService.findAllByNome(nome, pageable));
        }else {
            return ResponseEntity.ok(cupomService.findAll(pageable));
        }
    }


}


