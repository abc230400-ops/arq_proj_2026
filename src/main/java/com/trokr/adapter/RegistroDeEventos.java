package com.trokr.adapter;

import java.util.List;

import com.trokr.model.LogEvento;

public interface RegistroDeEventos {

    void registrar(LogEvento evento);

    List<LogEvento> buscarPorTipo(String tipo);

    List<LogEvento> buscarTodos();

}