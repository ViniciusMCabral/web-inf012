package br.edu.ifba.sistema.dtos;

import br.edu.ifba.sistema.entities.Disciplina;

public record DisciplinaDTO(Long id, String nome, String codigo) {
	
	public DisciplinaDTO(Disciplina disciplina) {
		this(disciplina.getId(), disciplina.getNome(), disciplina.getCodigo());
	}

}
