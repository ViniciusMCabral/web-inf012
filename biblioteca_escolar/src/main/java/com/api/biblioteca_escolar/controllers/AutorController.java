package com.api.biblioteca_escolar.controllers;

import java.net.URI;
import java.util.List;

import com.api.biblioteca_escolar.dtos.LivroDTO;
import com.api.biblioteca_escolar.services.LivroService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.biblioteca_escolar.dtos.AutorDTO;
import com.api.biblioteca_escolar.services.AutorService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/autores")
public class AutorController {

	private AutorService autorService;
	private LivroService livroService;

	public AutorController(AutorService autorService, LivroService livroService) {
		super();
		this.autorService = autorService;
		this.livroService = livroService;
	}
	
	@PostMapping
	public ResponseEntity<AutorDTO> criar(@RequestBody AutorDTO autorDTO, UriComponentsBuilder uriBuilder) {
		AutorDTO autor = autorService.criar(autorDTO);
		URI uri = uriBuilder.path("/autores{id}").buildAndExpand(autor.id()).toUri();
		return ResponseEntity.created(uri).body(autor);
	}
	
	@GetMapping
	public List<AutorDTO> listar() {
		return autorService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDTO> listarPorId(@PathVariable Long id) {
		AutorDTO autor = autorService.listarPorId(id);
		
		if (autor == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(autor);		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody AutorDTO autorDTO) {
		AutorDTO autor = autorService.atualizar(id, autorDTO);

		if (autor == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(autor);
	}
	
	@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        AutorDTO autor = autorService.remover(id);

        if (autor == null) {
			return ResponseEntity.notFound().build();
		}
		
        return ResponseEntity.noContent().build();
    }

	@GetMapping("/{id}/livros")
	public ResponseEntity<List<LivroDTO>> listarLivrosDoAutor(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Page<LivroDTO> paginaLivros = livroService.listarLivrosPorAutor(id, page, size);

		if (paginaLivros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(paginaLivros.getContent());
	}
}
