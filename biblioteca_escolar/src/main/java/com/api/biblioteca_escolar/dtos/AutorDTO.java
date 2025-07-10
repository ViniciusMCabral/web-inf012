package com.api.biblioteca_escolar.dtos;

import com.api.biblioteca_escolar.entities.Autor;

public record AutorDTO(Long id, String nome, String email, String nacionalidade) {

	public AutorDTO(Autor autor) {
		this(autor.getId(), autor.getNome(), autor.getEmail(), autor.getNacionalidade());
	}
}
