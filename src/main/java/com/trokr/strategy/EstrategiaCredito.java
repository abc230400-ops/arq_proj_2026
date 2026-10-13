package com.trokr.strategy;

import com.trokr.model.CategoriaItem;
import com.trokr.model.Item;

public interface EstrategiaCredito {

    CategoriaItem getCategoria();

    int calcular(Item item);
}