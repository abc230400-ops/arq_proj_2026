package com.trokr.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historico")
@Getter
@Setter
@NoArgsConstructor
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = false)
    private Proposta proposta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status statusNovo;

    @Column(nullable = false)
    private LocalDateTime dataModificacao;

    public void registrar() {
        this.dataModificacao = LocalDateTime.now();
    }
}