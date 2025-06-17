package br.edu.ifba.sistema.entities;

import java.util.List;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "disciplinas")
public class Disciplina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String codigo;
	
	 @OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
	 private List<Nota> notas;
	
	public Disciplina() {
		super();
	}
	
	public Disciplina(Long id, String nome, String codigo) {
		super();
		this.id = id;
		this.nome = nome;
		this.codigo = codigo;
	}
	
	public Disciplina(DisciplinaDTO disciplinaDTO) {
		this.id = disciplinaDTO.id();
		this.nome = disciplinaDTO.nome();
		this.codigo = disciplinaDTO.codigo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	

}
