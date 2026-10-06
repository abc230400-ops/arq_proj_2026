package com.trokr.controller;

import com.trokr.dto.PropostaRequestDTO;
import com.trokr.dto.PropostaResponseDTO;
import com.trokr.model.Proposta;
import com.trokr.service.PropostaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propostas")
@RequiredArgsConstructor
public class PropostaController {

    private final PropostaService propostaService;

    @GetMapping
    public List<PropostaResponseDTO> listar() {
        return propostaService.listarTodas().stream()
                .map(PropostaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public PropostaResponseDTO buscarPorId(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@Valid @RequestBody PropostaRequestDTO dto) {
        Proposta salva = propostaService.criar(dto.itemId(), dto.usuarioId(), dto.propostaAnteriorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(PropostaResponseDTO.fromEntity(salva));
    }

    @PatchMapping("/{id}/avancar")
    public PropostaResponseDTO avancar(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.avancar(id));
    }

    @PatchMapping("/{id}/recuar")
    public PropostaResponseDTO recuar(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.recuar(id));
    }

    @PatchMapping("/{id}/cancelar")
    public PropostaResponseDTO cancelar(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.cancelar(id));
    }

    @PatchMapping("/{id}/finalizar")
    public PropostaResponseDTO finalizar(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.finalizar(id));
    }

    @PatchMapping("/{id}/recusar")
    public PropostaResponseDTO recusar(@PathVariable Long id) {
        return PropostaResponseDTO.fromEntity(propostaService.recusar(id));
    }

}