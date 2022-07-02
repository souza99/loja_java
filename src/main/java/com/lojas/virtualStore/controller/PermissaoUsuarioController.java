package com.lojas.virtualStore.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lojas.virtualStore.domain.PermissaoUsuario;
import com.lojas.virtualStore.service.PermissaoUsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/permissaoUsuario")
public class PermissaoUsuarioController {
	
	@Autowired
    private PermissaoUsuarioService permissaoUsuarioService;
    
    
    @GetMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PermissaoUsuario>> findAll(Pageable pageable) {           	
            return ResponseEntity.ok(permissaoUsuarioService.findAll(pageable));        
    }
 

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermissaoUsuario> findById(@PathVariable long id) {    	
        	PermissaoUsuario permissaoUsuario = permissaoUsuarioService.findById(id);
            return ResponseEntity.ok(permissaoUsuario);        
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<PermissaoUsuario> add(@RequestBody PermissaoUsuario permissaoUsuario) 
            throws URISyntaxException {        
        	PermissaoUsuario usuarioNovo = permissaoUsuarioService.save(permissaoUsuario);
            return ResponseEntity.created(new URI("/api/permissaoUsuario/" + usuarioNovo.getId()))
                    .body(usuarioNovo);      
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<PermissaoUsuario> update(@Valid @RequestBody PermissaoUsuario permissaoUsuario, 
            @PathVariable long id) {       
            permissaoUsuario.setId(id);
            permissaoUsuarioService.update(permissaoUsuario);
            return ResponseEntity.ok().build();       
    }    
  
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {        
            permissaoUsuarioService.deleteById(id);
            return ResponseEntity.ok().build();       
    }
    
}
