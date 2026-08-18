package com.trokr.repository;

import com.trokr.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Uso direto do Spring Data JPA, sem interface/abstração genérica de
// repositório por cima — não há necessidade disso ainda.
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public void findByNomeOrId(String nome, long id);
    

}
