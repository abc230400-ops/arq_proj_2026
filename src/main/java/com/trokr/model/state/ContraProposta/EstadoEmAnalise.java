package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;
import com.trokr.model.state.Proposta.EstadoRascunho;

public class EstadoEmAnalise implements EstadoContraProposta {

    public void avancar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoNegociadoContra(), Status.NEGOCIADO_CONTRA); // certo

    }

    public void recuar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoRascunho(), Status.RASCUNHO_CONTRA);
    }

    public void cancelar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoCanceladoContra(), Status.CANCELADO_CONTRA);
    }

    public void finalizar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoRecusado(), Status.RECUSADO);
    }

}