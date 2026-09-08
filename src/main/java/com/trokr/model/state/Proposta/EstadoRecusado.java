package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public class EstadoRecusado implements EstadoProposta {

    @Override
    public void avancar(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'avancar'");
    }

    @Override
    public void recuar(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recuar'");
    }

    @Override
    public void cancelar(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelar'");
    }

    // @Override
    // public void contrapropor(Item item) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'contrapropor'");
    // }

    @Override
    public void finalizada(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'finalizada'");
    }

    @Override
    public void recusar(Item item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recusar'");
    }
    
}
