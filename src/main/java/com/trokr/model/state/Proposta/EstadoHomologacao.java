package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoHomologacao implements EstadoProposta {

    @Override
    public void avancar(Item item) {

        item.mudarEstadoPara(new EstadoAtiva());

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

    //     throw new IllegalStateException("Não permitido");
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
