package br.edu.ifba.sistema.entities;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import br.edu.ifba.sistema.dtos.NotaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "notas")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String aluno;
	private double nota;
	@ManyToOne
	private Disciplina disciplina;
	
	public Nota() {
		super();
	}
	
	public Nota(Long id, String aluno, double nota, Disciplina disciplina) {
		super();
		this.id = id;
		this.aluno = aluno;
		this.nota = nota;
		this.disciplina = disciplina;
	}

	public Nota(NotaDTO notaDTO) {
		this.id = notaDTO.id();
		this.aluno = notaDTO.aluno();
		this.nota = notaDTO.nota();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAluno() {
		return aluno;
	}

	public void setAluno(String aluno) {
		this.aluno = aluno;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

}
