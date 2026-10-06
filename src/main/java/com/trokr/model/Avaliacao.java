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

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private LocalDateTime data;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_dono_id", nullable = false)
    private Usuario usuarioDono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_interessado_id", nullable = false)
    private Usuario usuarioInteressado;

    public void registrarAvaliacao() {
        this.data = LocalDateTime.now();
    }
}