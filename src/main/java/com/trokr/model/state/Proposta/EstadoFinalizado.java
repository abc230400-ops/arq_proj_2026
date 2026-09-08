package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoFinalizado implements EstadoProposta {

    @Override
    public void avancar(Item item) {
        
        throw new IllegalStateException("Não permitido");

    }

    @Override
    public void recuar(Item item) {
        
        throw new IllegalStateException("Não permitido");

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
        
        throw new IllegalStateException("Proposta Finalizada!");

    }

    @Override
    public void recusar(Item item) {
        
        throw new IllegalStateException("Não permitido");
    }
    
}
