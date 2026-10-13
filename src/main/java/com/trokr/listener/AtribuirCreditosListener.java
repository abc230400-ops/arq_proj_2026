package com.trokr.listener;

import com.trokr.event.TrocaConcluidaEvent;
import com.trokr.model.Item;
import com.trokr.model.Proposta;
import com.trokr.model.Usuario;
import com.trokr.repository.PropostaRepository;
import com.trokr.repository.UsuarioRepository;
import com.trokr.service.CalculadoraCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AtribuirCreditosListener {

    private final PropostaRepository propostaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CalculadoraCreditoService calculadora;

    @EventListener
    public void aoConcluirTroca(TrocaConcluidaEvent event) {
        Proposta contraproposta = propostaRepository.findById(event.propostaId()).orElseThrow();
        Proposta original = contraproposta.getPropostaAnterior();

        // cada um ganha pelo item que ofereceu
        Item itemInteressado = contraproposta.getItem();
        Item itemDono = original.getItem();

        Usuario interessado = usuarioRepository.findById(event.usuarioInteressadoId()).orElseThrow();
        Usuario dono = usuarioRepository.findById(event.usuarioDonoId()).orElseThrow();

        interessado.adicionarCreditos(calculadora.calcular(itemInteressado));
        dono.adicionarCreditos(calculadora.calcular(itemDono));

        usuarioRepository.save(interessado);
        usuarioRepository.save(dono);
    }
}