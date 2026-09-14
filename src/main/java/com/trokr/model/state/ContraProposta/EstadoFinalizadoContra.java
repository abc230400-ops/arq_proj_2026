package com.trokr.model.state.ContraProposta;

import com.trokr.model.Item;

public class EstadoFinalizadoContra implements EstadoContraProposta {

    public void avancar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void finalizar(Item item) {

        item.mudarEstadoPara(new EstadoFinalizadoContra());
    }

    public void recusar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

}