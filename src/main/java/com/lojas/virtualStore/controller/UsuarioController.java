package com.lojas.virtualStore.controller;

import com.lojas.virtualStore.DTO.UsuarioDTO;
import com.lojas.virtualStore.domain.Usuario;
import com.lojas.virtualStore.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lojas.virtualStore.service.BadResourceException;
import com.lojas.virtualStore.service.ResourceAlreadyExistsException;
import com.lojas.virtualStore.service.ResourceNotFoundException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api-usuario")
@Tag(name = "usuario", description = "API para crud de usuarios")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Busca uma lista de usuarios", description = "Busca uma lista de produtos com por nome ou geral")
    @GetMapping(value = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioDTO>> findAll(
            @Parameter(description = "Nome do cpf_cnpj")
            @RequestBody(required = false) String cpfCnpj, Pageable pageable){
        if(StringUtils.isEmpty(cpfCnpj)){
            return ResponseEntity.ok(usuarioService.findAll(pageable));
        }else {
            return ResponseEntity.ok(usuarioService.findAllByCpfCnpj(cpfCnpj, pageable));
        }
    }



    @GetMapping(value = "/usuario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findProdutosById(@PathVariable long id){

        try{
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);

        }catch(ResourceNotFoundException ex) {
            logger.error(ex.getMessage());

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @PostMapping(value = "/usuario")
        public  ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario)
            throws URISyntaxException {

        try{
            Usuario novoUsuario = usuarioService.save(usuario);
            return ResponseEntity.created(new URI("/api-usuario/usuario/" + novoUsuario.getId())).body(usuario);

        }catch (ResourceAlreadyExistsException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (BadResourceException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }



    @PutMapping(value = "/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario,
                                                 @PathVariable long id){
        try{
            usuario.setId(id);
            usuarioService.update(usuario);
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.notFound().build();
        }catch (BadResourceException ex){
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping(value = "/usuario/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable long id){

        try {
            usuarioService.deletedById(id);
            return ResponseEntity.ok().build();
        }catch(ResourceNotFoundException ex){
            logger.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }

    }


}
