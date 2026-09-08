package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoNegociado implements EstadoProposta{

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
        
        throw new IllegalStateException("Não permitido");
    }

    // @Override
    // public void contrapropor(Item item) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'contrapropor'");
    // }

    @Override
    public void finalizada(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Item item) {
        
        item.mudarEstadoPara(new EstadoRecusado());

    }
    
}
