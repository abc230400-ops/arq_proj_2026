package com.trokr.service;

import com.trokr.exception.ResourceNotFoundException;
import com.trokr.model.Item;
import com.trokr.model.Proposta;
import com.trokr.model.Status;
import com.trokr.model.Usuario;
import com.trokr.model.state.ContraProposta.EstadoRascunhoContra;
import com.trokr.repository.PropostaRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropostaService {

    private final PropostaRepository propostaRepository;
    private final ItemService itemService;
    private final UsuarioService usuarioService;

    public List<Proposta> listarTodas() {
        return propostaRepository.findAll();
    }

    public Proposta buscarPorId(Long id) {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta não encontrada com id " + id));
    }

    public Proposta criar(Long itemId, Long usuarioId, Long propostaAnteriorId) {
        Item item = itemService.buscarPorId(itemId);
        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        Proposta proposta = new Proposta();
        proposta.setItem(item);
        proposta.setUsuario(usuario);

        if (propostaAnteriorId != null) {
            Proposta anterior = buscarPorId(propostaAnteriorId);
            proposta.setPropostaAnterior(anterior);
            proposta.setStatus(Status.RASCUNHO_CONTRA);
            proposta.setEstadoContra(new EstadoRascunhoContra());
            proposta.setEstadoAtual(null);
        }

        return propostaRepository.save(proposta);
    }

    public Proposta avancar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.avancar();
        return propostaRepository.save(proposta);
    }

    public Proposta recuar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.recuar();
        return propostaRepository.save(proposta);
    }

    public Proposta cancelar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.cancelar();
        return propostaRepository.save(proposta);
    }

    public Proposta finalizar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.finalizar();
        Proposta salva = propostaRepository.save(proposta);

        if (proposta.isContraproposta()) {
            recusarConcorrentes(proposta);
        }

        return salva;
    }

    private void recusarConcorrentes(Proposta aceita) {
        Long propostaOriginalId = aceita.getPropostaAnterior().getId();
        List<Proposta> concorrentes = propostaRepository.findByPropostaAnteriorId(propostaOriginalId);

        for (Proposta concorrente : concorrentes) {
            if (!concorrente.getId().equals(aceita.getId())
                    && concorrente.getStatus() != Status.RECUSADO
                    && concorrente.getStatus() != Status.CANCELADO_CONTRA) {
                concorrente.recusar();
                propostaRepository.save(concorrente);
            }
        }
    }

    public Proposta recusar(Long id) {
        Proposta proposta = buscarPorId(id);
        proposta.recusar();
        return propostaRepository.save(proposta);
    }
}