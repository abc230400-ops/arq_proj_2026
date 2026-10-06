package com.trokr.repository;

import com.trokr.model.Proposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    List<Proposta> findByItemId(Long itemId);
    List<Proposta> findByUsuarioId(Long usuarioId);
    List<Proposta> findByPropostaAnteriorId(Long propostaAnteriorId);

}