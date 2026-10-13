package com.trokr.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trokr.model.LogEvento;
import com.trokr.service.LogEventoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogEventoController {

    private final LogEventoService service;

    @GetMapping
    public List<LogEvento> listar() {
        return service.listar();
    }
}