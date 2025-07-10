package com.api.biblioteca_escolar.dtos;

import com.api.biblioteca_escolar.entities.Livro;

public record LivroDTO(Long id, String titulo, String isbn, Integer anoPublicacao, String autor) {
	
	public LivroDTO(Livro livro) {
		this(livro.getId(), livro.getTitulo(), livro.getIsbn(), livro.getAnoPublicacao(), livro.getAutor().getNome());
	}
}
