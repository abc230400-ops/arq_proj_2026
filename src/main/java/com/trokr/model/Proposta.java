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

// @Entity diz que essa classe vira uma tabela no banco.
// @Table diz o nome dela: "propostas".
@Entity
@Table(name = "propostas")
@Getter // Lombok gera automaticamente todos os métodos get (getId, getItem, etc.)
@Setter // Lombok gera automaticamente todos os métodos set (setId, setItem, etc.)
public class Proposta {

    // Identificador único de cada proposta no banco (a "chave primária").
    // GenerationType.IDENTITY = o próprio banco escolhe o próximo número.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Se essa proposta for uma CONTRAPROPOSTA, aqui fica guardada a
    // proposta original que ela está respondendo. Se for a proposta
    // original (a primeira), esse campo fica vazio (null).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposta_anterior_id")
    private Proposta propostaAnterior;

    // Qual Item está sendo negociado nessa proposta.
    // "nullable = false" = toda proposta TEM que ter um item.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    // Qual Usuario criou essa proposta. Serve tanto pra proposta original
    // (o dono que cria) quanto pra contraproposta (quem contrapropôs).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Lista de todas as contrapropostas que foram feitas EM CIMA dessa
    // proposta. "mappedBy" diz que quem controla essa ligação é o campo
    // "propostaAnterior" lá em cima.
    @OneToMany(mappedBy = "propostaAnterior", cascade = CascadeType.ALL)
    private List<Proposta> contrapropostas = new ArrayList<>();

    // Lista de todo o histórico de mudanças de status dessa proposta.
    @OneToMany(mappedBy = "proposta", cascade = CascadeType.ALL)
    private List<Historico> historico = new ArrayList<>();

    // Data em que a proposta foi criada. É preenchida sozinha pelo
    // Hibernate (@CreationTimestamp) e nunca pode ser alterada depois
    // (updatable = false).
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    // O status "oficial" da proposta, salvo no banco como texto
    // (EnumType.STRING), por exemplo "RASCUNHO" ou "ATIVA".
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Esses dois campos abaixo são o "objeto de comportamento" do estado
    // atual — eles sabem O QUE FAZER quando a proposta avança, recusa, etc.
    // @Transient = NÃO são salvos no banco (só o "status" acima é salvo).
    // Só UM dos dois fica preenchido por vez: se for proposta normal, usa
    // estadoAtual; se for contraproposta, usa estadoContra.
    @Transient
    private EstadoProposta estadoAtual;

    @Transient
    private EstadoContraProposta estadoContra;

    // Construtor: toda proposta nova começa como RASCUNHO.
    public Proposta() {
        this.status = Status.RASCUNHO;
        this.estadoAtual = new EstadoRascunho();
    }

    // Diz se essa proposta é uma contraproposta.
    // Regra: se ela tem uma "propostaAnterior", é contraproposta.
    public boolean isContraproposta() {
        return this.propostaAnterior != null;
    }

    // Esse método roda SOZINHO toda vez que o Hibernate carrega uma
    // Proposta do banco de dados. Como "estadoAtual"/"estadoContra" não
    // são salvos (são @Transient), eles ficam null depois de carregar —
    // esse método reconstrói o objeto certo, olhando pro "status" salvo.
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

    // Traduz o status salvo (texto) pro objeto de estado certo,
    // quando a proposta é a ORIGINAL (não é contraproposta).
    private EstadoProposta carregarEstadoProposta() {
        return switch (this.status) {
            case RASCUNHO -> new EstadoRascunho();
            case HOMOLOGACAO -> new EstadoHomologacao();
            case ATIVA -> new EstadoAtiva();
            case NEGOCIADO -> new EstadoNegociado();
            case FINALIZADO -> new EstadoFinalizado();
            case CANCELADO -> new EstadoCancelado();
            // Se aparecer um status que não devia existir pra proposta
            // original (por exemplo, um status só de contraproposta),
            // lança um erro avisando, em vez de deixar passar silencioso.
            default -> throw new IllegalStateException(
                    "Status inválido para proposta original: " + status);
        };
    }

    // Mesma ideia do método acima, só que pros status possíveis de uma
    // CONTRAPROPOSTA.
    private EstadoContraProposta carregarEstadoContra() {
        return switch (this.status) {
            case RASCUNHO_CONTRA -> new EstadoRascunhoContra();
            case EM_ANALISE -> new EstadoEmAnalise();
            case NEGOCIADO_CONTRA -> new EstadoNegociadoContra();
            case RECUSADO -> new EstadoRecusado();
            case FINALIZADO_CONTRA -> new EstadoFinalizadoContra();
            case CANCELADO_CONTRA -> new EstadoCanceladoContra();
            default -> throw new IllegalStateException(
                    "Status inválido para contraproposta: " + status);
        };
    }

    // Troca o estado da proposta ORIGINAL para um novo estado.
    // Faz TRÊS coisas juntas, sempre: registra no histórico, atualiza o
    // objeto de comportamento (estadoAtual) e atualiza o status salvo.
    public void mudarEstadoPara(EstadoProposta novoEstado, Status novoStatus) {
        registrarHistorico(novoStatus);
        this.estadoAtual = novoEstado;
        this.status = novoStatus;
    }

    // Mesma ideia, só que pra quando a proposta é uma CONTRAPROPOSTA.
    // O Java escolhe automaticamente qual dos dois métodos usar, olhando
    // o tipo do primeiro parâmetro que foi passado.
    public void mudarEstadoPara(EstadoContraProposta novoEstado, Status novoStatus) {
        registrarHistorico(novoStatus);
        this.estadoContra = novoEstado;
        this.status = novoStatus;
    }

    // Cria um novo registro de Historico toda vez que o status muda,
    // guardando qual era o status antes e qual é o novo.
    private void registrarHistorico(Status novoStatus) {
        Historico registro = new Historico();
        registro.setProposta(this);
        registro.setStatusAnterior(this.status);
        registro.setStatusNovo(novoStatus);
        registro.registrar();
        this.historico.add(registro);
    }

    // --- Os cinco métodos abaixo são a "porta de entrada" pra mudar o
    // estado da proposta de fora da classe (ex: lá do Controller).
    // Cada um verifica se é contraproposta ou não, e manda a ação pro
    // objeto de estado certo, que decide o que fazer. ---

    public void avancar() {
        if (isContraproposta())
            this.estadoContra.avancar(this);
        else
            this.estadoAtual.avancar(this);
    }

    public void recuar() {
        if (isContraproposta())
            this.estadoContra.recuar(this);
        else
            this.estadoAtual.recuar(this);
    }

    public void cancelar() {
        if (isContraproposta())
            this.estadoContra.cancelar(this);
        else
            this.estadoAtual.cancelar(this);
    }

    public void finalizar() {
        if (isContraproposta())
            this.estadoContra.finalizar(this);
        else
            this.estadoAtual.finalizar(this);
    }

    public void recusar() {
        if (isContraproposta())
            this.estadoContra.recusar(this);
        else
            this.estadoAtual.recusar(this);
    }
}