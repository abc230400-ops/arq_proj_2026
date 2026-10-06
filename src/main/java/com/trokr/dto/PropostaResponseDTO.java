package com.trokr.dto;

import com.trokr.model.Proposta;
import com.trokr.model.Status;
import java.time.LocalDateTime;

public record PropostaResponseDTO(
        Long id,
        Status status,
        Long itemId,
        String itemNome,
        Long usuarioId,
        String usuarioNome,
        Long propostaAnteriorId,
        LocalDateTime dataCriacao
) {

    public static PropostaResponseDTO fromEntity(Proposta proposta) {
        return new PropostaResponseDTO(
                proposta.getId(),
                proposta.getStatus(),
                proposta.getItem().getId(),
                proposta.getItem().getNome(),
                proposta.getUsuario().getId(),
                proposta.getUsuario().getNome(),
                proposta.getPropostaAnterior() != null ? proposta.getPropostaAnterior().getId() : null,
                proposta.getDataCriacao()
        );
    }
}