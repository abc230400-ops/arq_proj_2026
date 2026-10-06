package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;

public class EstadoFinalizado implements EstadoProposta {

    @Override
    public void avancar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");

    }

    @Override
    public void recuar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");

    }

    @Override
    public void cancelar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");
    }


    @Override
    public void finalizar(Proposta proposta) {
        
        throw new IllegalStateException("Proposta Finalizada!");

    }

    @Override
    public void recusar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");
    }
    
}
