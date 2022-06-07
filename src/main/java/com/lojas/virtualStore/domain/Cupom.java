package com.lojas.virtualStore.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.expression.spel.ast.Identifier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Cupom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
public class Cupom implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hash")
    private String hash;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "usuario")
    private Usuario usuario;

    @Column(name = "produto")
    private Produto produto;

    @Column(name = "valor")
    private double valor;

    @Column(name = "nome")
    private String nome;

}
