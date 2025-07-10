package com.api.biblioteca_escolar.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.biblioteca_escolar.dtos.LivroDTO;
import com.api.biblioteca_escolar.services.LivroService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/livros")
public class LivroController {

	private LivroService livroService;

	public LivroController(LivroService livroService) {
		super();
		this.livroService = livroService;
	}

	@PostMapping
	public ResponseEntity<?> criar(@RequestBody LivroDTO livroDTO, UriComponentsBuilder uriBuilder) {
		try {
			LivroDTO livro = livroService.criar(livroDTO);
			URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.id()).toUri();
			return ResponseEntity.created(uri).body(livro);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}


	@GetMapping
	public List<LivroDTO> listar() {
		return livroService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> listarPorId(@PathVariable Long id) {
		LivroDTO livro = livroService.listarPorId(id);
		
		if (livro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(livro);		
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
		try {
			LivroDTO livro = livroService.atualizar(id, livroDTO);

			if (livro == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(livro);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        LivroDTO livro = livroService.remover(id);

        if (livro == null) {
			return ResponseEntity.notFound().build();
		}
		
        return ResponseEntity.noContent().build();
    }

	@GetMapping("/search")
	public ResponseEntity<List<LivroDTO>> buscarLivrosPorTitulo(@RequestParam String titulo, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Page<LivroDTO> paginaLivros = livroService.buscarPorTitulo(titulo, page, size);

		if (paginaLivros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(paginaLivros.getContent());
	}
}
