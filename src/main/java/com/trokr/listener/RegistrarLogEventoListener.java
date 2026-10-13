package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.LogEvento;
import com.trokr.service.LogEventoService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrarLogEventoListener {

    private final LogEventoService logEventoService;

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent event) {
        // dados soltos da troca (o Mongo aceita qualquer formato)
        Map<String, Object> payload = new HashMap<>();
        payload.put("propostaId", event.propostaId());
        payload.put("itemId", event.itemId());
        payload.put("usuarioDonoId", event.usuarioDonoId());
        payload.put("usuarioInteressadoId", event.usuarioInteressadoId());

        LogEvento log = new LogEvento();
        log.setTipo("TROCA_CONCLUIDA");
        log.setNivel("INFO");
        log.setOrigem("PropostaService");
        log.setTimestamp(LocalDateTime.now());
        log.setPayload(payload);

        logEventoService.registrar(log);
    }
}