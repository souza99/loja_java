package com.lojas.virtualStore.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto_preco")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class ProdutoPreco {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private Date dataCadastro = new Date();
	 
	@ManyToOne
	@JoinColumn(name ="id_produto")
	private Produto produto;

	private Double valorCusto;
	private Double valorVenda;
}