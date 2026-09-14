package com.trokr.model.state.ContraProposta;

import com.trokr.model.Item;

public class EstadoCanceladoContra implements EstadoContraProposta {

    public void avancar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Item item) {

        item.mudarEstadoPara(new EstadoCanceladoContra());
    }

    public void finalizar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

}
