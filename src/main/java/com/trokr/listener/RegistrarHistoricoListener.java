package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrarHistoricoListener {

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent event) {
        log.info("[HISTORICO] Troca concluida: proposta {} (item {}). O historico de status ja foi gravado em Proposta.mudarEstadoPara.",
                event.propostaId(), event.itemId());
    }
}