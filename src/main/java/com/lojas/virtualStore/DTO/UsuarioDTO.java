package com.lojas.virtualStore.DTO;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.lojas.virtualStore.domain.Usuario;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String cpfCnpf;
	
	public UsuarioDTO converter(Usuario usuario){
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		BeanUtils.copyProperties(usuario, usuarioDTO);
		return this;
	}
	
	
	public Page<UsuarioDTO> converterListaUsuarioDTO(Page<Usuario> pageUsuario){
		Page<UsuarioDTO> listaDTO = pageUsuario.map(this::converter);
		return listaDTO;
	}
	

}
