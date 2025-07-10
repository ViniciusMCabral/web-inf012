package com.api.biblioteca_escolar.services;

import java.util.List;

import com.api.biblioteca_escolar.exceptions.AutorComLivrosException;
import org.springframework.stereotype.Service;

import com.api.biblioteca_escolar.dtos.AutorDTO;
import com.api.biblioteca_escolar.entities.Autor;
import com.api.biblioteca_escolar.repositories.AutorRepository;

@Service
public class AutorService {

	private AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		super();
		this.autorRepository = autorRepository;
	}

	public AutorDTO criar(AutorDTO autorDTO) {
		return new AutorDTO(autorRepository.save(new Autor(autorDTO)));
	}

	public List<AutorDTO> listar() {
		return autorRepository.findAll().stream().map(AutorDTO::new).toList();
	}

	public AutorDTO listarPorId(Long id) {
		return autorRepository.findById(id).map(AutorDTO::new).orElse(null);
	}

	public AutorDTO atualizar(Long id, AutorDTO autorDTO) {
		Autor autor = autorRepository.findById(id).orElse(null);

		if (autor == null) {
			return null;
		}

		autor.setNome(autorDTO.nome());
		autor.setEmail(autorDTO.email());
		autor.setNacionalidade(autorDTO.nacionalidade());

		return new AutorDTO(autorRepository.save(autor));
	}

	public AutorDTO remover(Long id) {
		Autor autor = autorRepository.findById(id).orElse(null);

		if (autor == null) {
			return null;
		}

		if (autor.getLivros() != null && !autor.getLivros().isEmpty()) {
			throw new AutorComLivrosException("Autor possui livros associados e não pode ser removido.");
		}

		autorRepository.delete(autor);
		return new AutorDTO(autor);
	}
}
