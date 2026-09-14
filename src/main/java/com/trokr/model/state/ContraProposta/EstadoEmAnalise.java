package com.trokr.model.state.ContraProposta;

import com.trokr.model.Item;
import com.trokr.model.state.Proposta.EstadoRascunho;

public class EstadoEmAnalise implements EstadoContraProposta {

    public void avancar(Item item) {

        item.mudarEstadoPara(new EstadoNegociadoContra());
    }

    public void recuar(Item item) {

        item.mudarEstadoPara(new EstadoRascunho());
    }

    public void cancelar(Item item) {

        item.mudarEstadoPara(new EstadoCanceladoContra());
    }

    public void finalizar(Item item) {

        throw new IllegalStateException("Não permitido");
    }

    public void recusar(Item item) {

        item.mudarEstadoPara(new EstadoRecusado());
    }

}