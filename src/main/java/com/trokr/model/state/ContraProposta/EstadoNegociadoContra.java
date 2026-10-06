package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoNegociadoContra implements EstadoContraProposta {

    public void avancar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoFinalizadoContra(), Status.FINALIZADO_CONTRA);
    }

    public void recuar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void finalizar(Proposta proposta) {
        proposta.mudarEstadoPara(new EstadoFinalizadoContra(), Status.FINALIZADO_CONTRA);
    }

    public void recusar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoRecusado(), Status.RECUSADO);
    }

}