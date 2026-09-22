package com.trokr.model;

import com.trokr.model.state.Proposta.EstadoProposta;
import com.trokr.model.state.Proposta.*;
import com.trokr.model.state.ContraProposta.EstadoContraProposta;
import com.trokr.model.state.ContraProposta.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "propostas")
@Getter
@Setter
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_anterior_id")
    private Proposta propostaAnterior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @OneToMany(mappedBy = "propostaAnterior", cascade = CascadeType.ALL)
    private List<Proposta> contrapropostas = new ArrayList<>();

    @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL)
    private List<Historico> historico = new ArrayList<>();

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProposta status;

    // Só UM destes fica preenchido, dependendo de isContraproposta()
    @Transient
    private EstadoProposta estadoAtual;

    @Transient
    private EstadoContraProposta estadoContra;

    public Proposta() {
        this.status = StatusProposta.RASCUNHO;
        this.estadoAtual = new EstadoRascunho();
    }

    public boolean isContraproposta() {
        return this.propostaAnterior != null;
    }

    @PostLoad
    public void carregarEstado() {
        if (this.status == null)
            return;

        if (isContraproposta()) {
            this.estadoContra = carregarEstadoContra();
            this.estadoAtual = null;
        } else {
            this.estadoAtual = carregarEstadoProposta();
            this.estadoContra = null;
        }
    }

    private EstadoProposta carregarEstadoProposta() {
        return switch (this.status) {
            case RASCUNHO -> new EstadoRascunho();
            case HOMOLOGACAO -> new EstadoHomologacao();
            case ATIVA -> new EstadoAtiva();
            case NEGOCIADO -> new EstadoNegociado();
            case FINALIZADO -> new EstadoFinalizado();
            case CANCELADO -> new EstadoCancelado();
            default -> throw new IllegalStateException(
                    "Status inválido para proposta original: " + status);
        };
    }

    private EstadoContraProposta carregarEstadoContra() {
        return switch (this.status) {
            case RASCUNHO -> new EstadoRascunhoContra();
            case EM_ANALISE -> new EstadoEmAnalise();
            case NEGOCIADO -> new EstadoNegociadoContra();
            case RECUSADO -> new EstadoRecusado();
            case FINALIZADO -> new EstadoFinalizadoContra();
            case CANCELADO -> new EstadoCanceladoContra();
            default -> throw new IllegalStateException(
                    "Status inválido para contraproposta: " + status);
        };
    }

    // --- Mudança de estado (proposta normal) ---
    public void mudarEstadoPara(EstadoProposta novoEstado, StatusProposta novoStatus) {
        registrarHistorico(novoStatus);
        this.estadoAtual = novoEstado;
        this.status = novoStatus;
    }

    // --- Mudança de estado (contraproposta) ---
    public void mudarEstadoPara(EstadoContraProposta novoEstado, StatusProposta novoStatus) {
        registrarHistorico(novoStatus);
        this.estadoContra = novoEstado;
        this.status = novoStatus;
    }

    private void registrarHistorico(StatusProposta novoStatus) {
        Historico registro = new Historico();
        registro.setProposta(this);
        registro.setStatusAnterior(this.status);
        registro.setStatusNovo(novoStatus);
        registro.registrar();
        this.historico.add(registro);
    }

    // --- Delegação (State Pattern) ---
    public void avancar() {
        if (isContraproposta())
            this.estadoContra.avancar(this.item);
        else
            this.estadoAtual.avancar(this.item);
    }

    public void recuar() {
        if (isContraproposta())
            this.estadoContra.recuar(this.item);
        else
            this.estadoAtual.recuar(this.item);
    }

    public void cancelar() {
        if (isContraproposta())
            this.estadoContra.cancelar(this.item);
        else
            this.estadoAtual.cancelar(this.item);
    }

    public void finalizar() {
        if (isContraproposta())
            this.estadoContra.finalizar(this.item);
        else
            this.estadoAtual.finalizar(this.item);
    }

    public void recusar() {
        if (isContraproposta())
            this.estadoContra.recusar(this.item);
        else
            this.estadoAtual.recusar(this.item);
    }
}