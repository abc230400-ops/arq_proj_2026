package com.trokr.dto;

import jakarta.validation.constraints.NotNull;

/**
 * Dados de entrada para criar uma Proposta.
 * O usuarioId identifica quem está criando a proposta (ou a contraproposta).
 */
public record PropostaRequestDTO(

        @NotNull(message = "itemId é obrigatório")
        Long itemId,

        @NotNull(message = "usuarioId é obrigatório")
        Long usuarioId,

        Long propostaAnteriorId // opcional: se vier preenchido, é uma contraproposta
        
) {
}