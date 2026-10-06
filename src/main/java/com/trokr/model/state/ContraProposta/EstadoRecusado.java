package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoRecusado implements EstadoContraProposta {

    public void avancar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void finalizar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoRecusado(), Status.RECUSADO);
    }
    
}
