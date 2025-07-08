package com.controle.gastos.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.controle.gastos.entities.Categoria;
import com.controle.gastos.entities.Gasto;

public record GastoDTO(Long id, String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {

	public GastoDTO(Gasto gasto) {
		this(gasto.getId(), gasto.getDescricao(), gasto.getValor(), gasto.getData(), gasto.getCategoria());
	}
}
