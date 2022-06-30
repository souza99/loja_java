package com.lojas.virtualStore.repository;

import com.lojas.virtualStore.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select a from Usuario a where a.cpfCnpj like %?1%")
    Page<Usuario> findByCpfCnpj(String cpfCnpf, Pageable page);

    Page<Usuario>findAll(Pageable pageable);

    Boolean existsByEmail(String emal);

    Boolean existsByCpfCnpj(String cpf);
}
