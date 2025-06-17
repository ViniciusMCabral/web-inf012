package br.edu.ifba.sistema.dtos;

import br.edu.ifba.sistema.entities.Nota;

public record NotaDTO(Long id, String aluno, double nota, String disciplina) {
	
	public NotaDTO(Nota nota) {
		this(nota.getId(), nota.getAluno(), nota.getNota(), nota.getDisciplina().getNome());
	}
}
