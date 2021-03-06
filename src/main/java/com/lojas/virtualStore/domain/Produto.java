package com.lojas.virtualStore.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "produto")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;
    
    private String marca;
    
    @Column(name = "valor")
    private double valor;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "valor_venda")
    private Double valorVenda;
    
    @Column(name = "valor_custo")
    private Double valorCusto;
    
    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;
    

}
