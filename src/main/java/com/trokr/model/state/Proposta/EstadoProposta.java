package com.trokr.model.state.Proposta;

import com.trokr.model.Item;

public interface EstadoProposta{

void avancar(Item item);
void recuar(Item item);
void cancelar(Item item);

// void contrapropor(Item item);
void finalizar(Item item);
void recusar(Item item);
//pensar nas assinaturas que cada metodo terá e ai escrever e depois passar pros estados

}