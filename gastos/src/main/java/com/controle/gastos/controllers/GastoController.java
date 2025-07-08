package com.controle.gastos.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.gastos.dtos.GastoDTO;
import com.controle.gastos.entities.Categoria;
import com.controle.gastos.services.GastoService;

@RestController
@RequestMapping("/gastos")
public class GastoController {

	private GastoService gastoService;
	
	public GastoController(GastoService gastoService) {
		this.gastoService = gastoService;
	}

	@PostMapping
	public ResponseEntity<GastoDTO> salvar(@RequestBody GastoDTO gastoDTO) {
		GastoDTO gastoSalvo = gastoService.salvar(gastoDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(gastoSalvo);
	}

	@GetMapping
	public ResponseEntity<List<GastoDTO>> listarTodos() {
		List<GastoDTO> gastos = gastoService.listarTodos();
		return ResponseEntity.ok(gastos);
	}
	
	@GetMapping("/filtrarData")
	public ResponseEntity<List<GastoDTO>> listarPorMesAno(@RequestParam int mes, @RequestParam int ano) {
		List<GastoDTO> gastos = gastoService.listarPorMesAno(mes, ano);
		return ResponseEntity.ok(gastos);
	}
	
	@GetMapping("/filtrarCategoria")
	public ResponseEntity<List<GastoDTO>> listarPorCategoria(@RequestParam Categoria categoria) {
		List<GastoDTO> gastos = gastoService.listarPorCategoria(categoria);
		return ResponseEntity.ok(gastos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GastoDTO> atualizar(@RequestBody GastoDTO gastoDTO, @PathVariable Long id) {
		GastoDTO gastoAtualizado = gastoService.atualizar(gastoDTO, id);
		if (gastoAtualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(gastoAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		GastoDTO gastoDeletado = gastoService.deletar(id);
		if (gastoDeletado != null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/relatorio")
	public Map<String, BigDecimal> relatorioMensal(@RequestParam int mes, @RequestParam int ano) {
		return gastoService.relatorioMensal(mes, ano);
	}
}
