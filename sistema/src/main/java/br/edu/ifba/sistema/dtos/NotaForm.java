package br.edu.ifba.sistema.dtos;

import br.edu.ifba.sistema.entities.Nota;

public record NotaForm(Long id, String aluno, double nota, Long disciplina) {
	
	public NotaForm (Nota nota) {
		this(nota.getId(), nota.getAluno(), nota.getNota(), nota.getDisciplina().getId());
	}

}
