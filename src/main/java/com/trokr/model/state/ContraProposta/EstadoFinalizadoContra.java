package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoFinalizadoContra implements EstadoContraProposta {

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

        proposta.mudarEstadoPara(new EstadoFinalizadoContra(), Status.FINALIZADO_CONTRA);
    }

    public void recusar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

}