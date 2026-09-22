package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoCancelado implements EstadoProposta {

    public void avancar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recuar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void cancelar(Item item) {

       Proposta.mudarEstadoPara(new EstadoCancelado());
    }

    // public void contrapropor(Item item) {

    //     throw new UnsupportedOperationException("Unimplemented method 'contrapropor'");
    // }

    public void finalizar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Item item) {

        throw new IllegalStateException("Não permitido");
    }
    
}
