package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;
import com.trokr.model.Status;

public class EstadoRascunho implements EstadoProposta{
    //implementar as assinaturas na interface
    @Override
    public void avancar(Proposta proposta) {

        proposta.mudarEstadoPara(new EstadoHomologacao(), Status.HOMOLOGACAO);
    }

    @Override
    public void recuar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void cancelar(Proposta proposta) {
        
        proposta.mudarEstadoPara(new EstadoCancelado(), Status.CANCELADO);
    }
   
    @Override
    public void finalizar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");
    }

    @Override
    public void recusar(Proposta proposta) {
        
        throw new IllegalStateException("Não permitido");
    }
    
}