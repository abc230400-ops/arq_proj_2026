package com.trokr.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trokr.adapter.RegistroDeEventos;
import com.trokr.model.LogEvento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogEventoService {

    private final RegistroDeEventos registro;

    public void registrar(LogEvento evento) {
        registro.registrar(evento);
    }

    public List<LogEvento> listar() {
        return registro.buscarTodos();
    }
}