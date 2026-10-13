package com.trokr.service;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;
import com.trokr.strategy.EstrategiaCredito;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraCreditoService {

    private final Map<CategoriaItem, EstrategiaCredito> estrategias = new HashMap<>();

    // O Spring entrega aqui TODAS as classes marcadas com @Component
    // que implementam EstrategiaCredito (as 3 de cima).
    public CalculadoraCreditoService(List<EstrategiaCredito> listaEstrategias) {
        for (EstrategiaCredito estrategia : listaEstrategias) {
            estrategias.put(estrategia.getCategoria(), estrategia);
        }
    }

    public int calcular(Item item) {
        // itens antigos ainda não têm categoria
        if (item.getCategoria() == null) {
            return 0;
        }
        EstrategiaCredito estrategia = estrategias.get(item.getCategoria());
        return estrategia.calcular(item);
    }
}