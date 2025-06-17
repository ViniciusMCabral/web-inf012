package br.edu.ifba.sistema.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import br.edu.ifba.sistema.services.DisciplinaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {
	
	
	private DisciplinaService disciplinaService;
	
	public DisciplinaController(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}
	
	@GetMapping
	public List<DisciplinaDTO> getAll() {
		return disciplinaService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DisciplinaDTO> getById(@PathVariable Long id) {
		
		DisciplinaDTO disciplina = disciplinaService.findById(id);
		
		if (disciplina != null) {
			return ResponseEntity.ok(disciplina);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<DisciplinaDTO> create(@RequestBody DisciplinaDTO disciplinaDTO, UriComponentsBuilder uriBuilder) {
		DisciplinaDTO disciplina = disciplinaService.save(disciplinaDTO);
		URI location = uriBuilder.path("/api/disciplinas/{id}").buildAndExpand(disciplina.id()).toUri();
		return ResponseEntity.created(location).body(disciplina);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DisciplinaDTO> update(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
		DisciplinaDTO disciplina = disciplinaService.update(id, disciplinaDTO);
		
		if (disciplina != null) {
			return ResponseEntity.ok(disciplina);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		DisciplinaDTO disciplina = disciplinaService.delete(id);
		
		if (disciplina != null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
