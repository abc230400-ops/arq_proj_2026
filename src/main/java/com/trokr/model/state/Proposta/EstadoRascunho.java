package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoRascunho implements EstadoProposta{
    //implementar as assinaturas na interface
    @Override
    public void avancar(Item item){

        item.mudarEstadoPara(new EstadoHomologacao());
    }

    @Override
    public void recuar(Item item) {
        
        throw new IllegalStateException("Não permitido");
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
    public void finalizar(Item item) {
        
        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Item item) {
        
        throw new IllegalStateException("Não permitido");
    }
    
}