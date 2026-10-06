package com.trokr.model.state.ContraProposta;

import com.trokr.model.Proposta;

public interface EstadoContraProposta {

    public void avancar(Proposta proposta);

    public void recuar(Proposta proposta);

    public void cancelar(Proposta proposta);

    public void finalizar(Proposta proposta);

    public void recusar(Proposta proposta);

}