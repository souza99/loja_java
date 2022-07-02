package com.lojas.virtualStore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissao_usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class PermissaoUsuario implements Serializable{
	
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
      
    private Date dataCadastro = new Date();
   
    @ManyToOne
    private Usuario usuario;
    
    @ManyToOne
    private Permissao permissao;   
}