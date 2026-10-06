package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoCancelado implements EstadoProposta {

    public void avancar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Proposta proposta) {

       proposta.mudarEstadoPara(new EstadoCancelado(), Status.CANCELADO);
    }

    public void finalizar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }
    
}
