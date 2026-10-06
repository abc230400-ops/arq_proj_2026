package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoCanceladoContra implements EstadoContraProposta {

    public void avancar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoCanceladoContra(), Status.CANCELADO_CONTRA);
    }

    public void finalizar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Proposta proposta) {

        throw new IllegalStateException("Não permitido");
    }

}
