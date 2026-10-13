package com.trokr.adapter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.trokr.model.LogEvento;
import com.trokr.repository.LogEventoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RegistroDeEventosMongo implements RegistroDeEventos {

    private final LogEventoRepository repository;

    @Override
    public void registrar(LogEvento evento) {
        repository.save(evento);
    }

    @Override
    public List<LogEvento> buscarPorTipo(String tipo) {
        return repository.findByTipo(tipo);
    }

    @Override
    public List<LogEvento> buscarTodos() {
        return repository.findAll();
    }
}