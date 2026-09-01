package com.trokr.model.state;

public interface EstadoItem{

void avancar(Item item);
void recuar(Item item);
void cancelar(Item item);

//pensar nas assinaturas que cada metodo terá e ai escrever e depois passar pros estados 

}