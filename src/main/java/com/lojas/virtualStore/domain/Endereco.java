package com.lojas.virtualStore.domain;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "Endereco")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero_rua")
    private String numeroRua;

    @Column(name = "complemento")
    private String complemento;
    
    

}