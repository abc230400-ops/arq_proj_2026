package com.trokr.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer nota;

    private LocalDateTime data;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAvaliacao status = StatusAvaliacao.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_id", nullable = false)
    private Proposta proposta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_dono_id", nullable = false)
    private Usuario usuarioDono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_interessado_id", nullable = false)
    private Usuario usuarioInteressado;

    public void registrarAvaliacao(Integer nota, String descricao) {
        if (this.status == StatusAvaliacao.AVALIADA) {
            throw new IllegalStateException("Essa avaliação já foi feita");
        }
        this.nota = nota;
        this.descricao = descricao;
        this.data = LocalDateTime.now();
        this.status = StatusAvaliacao.AVALIADA;
    }
}