package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoNegociado implements EstadoProposta {

    @Override
    public void avancar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoFinalizado(), Status.FINALIZADO);

    }

    @Override
    public void recuar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoAtiva(), Status.ATIVA);

    }

    @Override
    public void cancelar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoCancelado(), Status.CANCELADO);
    }

    @Override
    public void finalizar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");

    }

}
