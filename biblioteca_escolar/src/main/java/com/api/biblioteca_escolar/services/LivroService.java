package com.api.biblioteca_escolar.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.biblioteca_escolar.dtos.LivroDTO;
import com.api.biblioteca_escolar.entities.Livro;
import com.api.biblioteca_escolar.repositories.LivroRepository;

@Service
public class LivroService {

	private LivroRepository livroRepository;

	public LivroService(LivroRepository livroRepository) {
		super();
		this.livroRepository = livroRepository;
	}
	
	public LivroDTO criar(LivroDTO livroDTO) {
		int anoAtual = java.time.Year.now().getValue();

		if (livroDTO.anoPublicacao() > anoAtual) {
			throw new IllegalArgumentException("O ano de publicação deve ser no máximo " + anoAtual);
		}

		return new LivroDTO(livroRepository.save(new Livro(livroDTO)));
	}
	
	public List<LivroDTO> listar() {
		return livroRepository.findAll()
				.stream()
				.map(LivroDTO::new)
				.toList();
	}
	
	public LivroDTO listarPorId(Long id) {
		return livroRepository.findById(id)
				.map(LivroDTO::new)
				.orElse(null);
	}
	
	public LivroDTO atualizar(Long id, LivroDTO livroDTO) {
		Livro livro = livroRepository.findById(id).orElse(null);
		
		if (livro == null) {
			return null;
		}

		int anoAtual = java.time.Year.now().getValue();

		if (livroDTO.anoPublicacao() > anoAtual) {
			throw new IllegalArgumentException("O ano de publicação deve ser no máximo " + anoAtual);
		}

		livro.setTitulo(livroDTO.titulo());
		livro.setIsbn(livroDTO.isbn());
		livro.setAnoPublicacao(livroDTO.anoPublicacao());
		
		return new LivroDTO(livroRepository.save(livro));
	}
	
	public LivroDTO remover(Long id) {
		Livro livro = livroRepository.findById(id).orElse(null);

			if (livro == null) {
			return null;
		}
		
		livroRepository.delete(livro);
		return new LivroDTO(livro);
	}

	public Page<LivroDTO> listarLivrosPorAutor(Long autorId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return livroRepository.findByAutorId(autorId, pageable)
				.map(LivroDTO::new);
	}

	public Page<LivroDTO> buscarPorTitulo(String titulo, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return livroRepository.findByTituloContainingIgnoreCase(titulo, pageable)
				.map(LivroDTO::new);
	}
}
