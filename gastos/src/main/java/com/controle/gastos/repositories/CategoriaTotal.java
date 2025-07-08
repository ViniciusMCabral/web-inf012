package com.controle.gastos.repositories;

import java.math.BigDecimal;

import com.controle.gastos.entities.Categoria;

public interface CategoriaTotal {
    Categoria getCategoria();
    BigDecimal getTotal();
}