package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.Avaliacao;
import com.trokr.model.Proposta;
import com.trokr.model.Usuario;
import com.trokr.repository.AvaliacaoRepository;
import com.trokr.repository.PropostaRepository;
import com.trokr.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtualizarAvaliacaoListener {

    private final AvaliacaoRepository avaliacaoRepository;
    private final PropostaRepository propostaRepository;
    private final UsuarioRepository usuarioRepository;

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent event) {
        Proposta proposta = propostaRepository.getReferenceById(event.propostaId());
        Usuario dono = usuarioRepository.getReferenceById(event.usuarioDonoId());
        Usuario interessado = usuarioRepository.getReferenceById(event.usuarioInteressadoId());

        // uma avaliação em cada direção
        avaliacaoRepository.save(novaAvaliacao(proposta, dono, interessado));
        avaliacaoRepository.save(novaAvaliacao(proposta, interessado, dono));
    }

    private Avaliacao novaAvaliacao(Proposta proposta, Usuario quemAvalia, Usuario quemEhAvaliado) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setProposta(proposta);
        avaliacao.setUsuarioDono(quemAvalia);
        avaliacao.setUsuarioInteressado(quemEhAvaliado);
        return avaliacao;
    }
}