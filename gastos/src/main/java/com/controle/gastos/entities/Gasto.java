package com.controle.gastos.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.controle.gastos.dtos.GastoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name = "gastos")
public class Gasto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	public Gasto() {
		super();
	}
	
	public Gasto(Long id, String descricao, BigDecimal valor, LocalDate data, Categoria categoria) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
		this.categoria = categoria;
	}
	
	public Gasto(GastoDTO gastoDTO) {
		this.id = gastoDTO.id();
		this.descricao = gastoDTO.descricao();
		this.valor = gastoDTO.valor();
		this.data = gastoDTO.data();
		this.categoria = gastoDTO.categoria();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
