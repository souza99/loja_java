package com.lojas.virtualStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lojas.virtualStore.domain.PermissaoUsuario;
import com.lojas.virtualStore.repository.PermissaoUsuarioRepository;

public class PermissaoUsuarioService {

	@Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;
    
    private boolean existsById(Long id) {
        return permissaoUsuarioRepository.existsById(id);
    }
    
    public PermissaoUsuario findById(Long id) {
    	PermissaoUsuario permissaoUsuario = permissaoUsuarioRepository.findById(id).orElse(null);
        return permissaoUsuario;
    }
    
    public Page<PermissaoUsuario> findAll(Pageable pageable) {
        return permissaoUsuarioRepository.findAll(pageable);
    }
   
    public PermissaoUsuario save(PermissaoUsuario permissaoUsuario) {
            return permissaoUsuarioRepository.save(permissaoUsuario);
    }
    
    public void update(PermissaoUsuario permissaoUsuario) {      
    	permissaoUsuarioRepository.save(permissaoUsuario);       
    }    
  
    
    public void deleteById(Long id)  {
        if (!existsById(id)) {         
        	permissaoUsuarioRepository.deleteById(id);
        }        
    }
    
    public Long count() {
        return permissaoUsuarioRepository.count();
    }
    
}
