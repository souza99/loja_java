package com.lojas.virtualStore.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Usuario")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String email;
    private String senha;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;
    
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name = "imagem_de_perfil", length = 16777215)
    private byte[] imagemDePerfil;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro = new Date();

}