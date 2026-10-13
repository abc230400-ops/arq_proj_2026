package com.trokr.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "logs_eventos")
public class LogEvento {

    @Id
    private String id;

    private String tipo;
    private String nivel; // vai sair um desses no log: "INFO", "WARN" ou "ERROR"
    private String origem;
    private Map<String, Object> payload;
    private LocalDateTime timestamp;
    private Long usuarioId; // opcional
}