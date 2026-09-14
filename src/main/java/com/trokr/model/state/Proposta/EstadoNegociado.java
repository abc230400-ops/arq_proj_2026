package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoNegociado implements EstadoProposta {

    @Override
    public void avancar(Item item) {

        item.mudarEstadoPara(new EstadoFinalizado());

    }

    @Override
    public void recuar(Item item) {

        item.mudarEstadoPara(new EstadoAtiva());

    }

    @Override
    public void cancelar(Item item) {

        item.mudarEstadoPara(new EstadoCancelado());
    }

    // @Override
    // public void contrapropor(Item item) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method
    // 'contrapropor'");
    // }

    @Override
    public void finalizar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Item item) {

        throw new IllegalStateException("Não permitido");

    }

}
