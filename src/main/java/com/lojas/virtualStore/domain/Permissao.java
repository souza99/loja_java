package com.lojas.virtualStore.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Getter;
import lombok.Setter;

	@Entity
	@Table(name = "permissao")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@Getter
	@Setter
	public class Permissao implements Serializable {
	 
	    private static final long serialVersionUID = 1L;
	 
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name ="nome")
	    private String nome;
	    
	    private Date dataCadastro = new Date();
	    
}
