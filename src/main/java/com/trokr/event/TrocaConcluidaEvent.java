package com.trokr.event;

import java.time.LocalDateTime;

public record TrocaConcluidaEvent(
        Long propostaId,
        Long itemId,
        Long usuarioDonoId,        // quem criou a proposta original
        Long usuarioInteressadoId, // quem fez a contraproposta aceita
        LocalDateTime dataConclusao
) {
}