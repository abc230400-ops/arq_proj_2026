package com.trokr.model.state.Proposta;

import com.trokr.model.Proposta;

public interface EstadoProposta{

void avancar(Proposta proposta);
void recuar(Proposta proposta);
void cancelar(Proposta proposta);

// void contrapropor(Proposta proposta);
void finalizar(Proposta proposta);
void recusar(Proposta proposta);
//pensar nas assinaturas que cada metodo terá e ai escrever e depois passar pros estados

}