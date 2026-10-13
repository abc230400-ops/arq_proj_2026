package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificarUsuariosListener {

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent event) {
        log.info("[NOTIFICACAO] Usuario {}: sua troca (proposta {}) foi concluida!",
                event.usuarioDonoId(), event.propostaId());
        log.info("[NOTIFICACAO] Usuario {}: sua troca (proposta {}) foi concluida!",
                event.usuarioInteressadoId(), event.propostaId());
    }
}