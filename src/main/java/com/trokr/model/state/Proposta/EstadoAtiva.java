package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoAtiva implements EstadoProposta {

    @Override
    public void avancar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoNegociado(), Status.NEGOCIADO);
    }

    @Override
    public void recuar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");

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
