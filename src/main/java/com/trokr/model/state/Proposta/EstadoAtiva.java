package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoAtiva implements EstadoProposta {

    @Override
    public void avancar(Item item) {

        item.mudarEstadoPara(new EstadoNegociado());
    }

    @Override
    public void recuar(Item item) {

        item.mudarEstadoPara(new EstadoRascunho());

    }

    @Override
    public void cancelar(Item item) {

        item.mudarEstadoPara(new EstadoCancelado());
    }

    // @Override
    // public void contrapropor(Item item) {

    //     item.mudarEstadoPara(new);

    // }

    @Override
    public void finalizada(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

}
