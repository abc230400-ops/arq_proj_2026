package com.trokr.model.state.ContraProposta;

import com.trokr.model.Item;

public class EstadoNegociadoContra implements EstadoContraProposta {

    public void avancar(Item item) {

        item.mudarEstadoPara(new EstadoFinalizadoContra());
    }

    public void recuar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Item item) {

       throw new IllegalStateException("Não permitido");
    }

    public void finalizar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Item item) {

        item.mudarEstadoPara(new EstadoRecusado());
    }

}