package com.trokr.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trokr.model.LogEvento;

public interface LogEventoRepository extends MongoRepository<LogEvento, String> {

    List<LogEvento> findByTipo(String tipo);
}