package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;

public interface EstadoContraProposta {

    public void avancar(Item item);

    public void recuar(Item item);

    public void cancelar(Item item);

    public void finalizar(Item item);

    public void recusar(Item item);

}