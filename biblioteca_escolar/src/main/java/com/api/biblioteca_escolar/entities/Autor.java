package com.api.biblioteca_escolar.entities;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import com.api.biblioteca_escolar.dtos.AutorDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "O nome não pode ser nulo") 
	@Length(min=2, max=100,message = "O nome tem min de 2 caracteres e max de 100 caracteres")
	private String nome;
	@NotNull(message = "O email não pode ser nulo") 
	@Email
	private String email;
	@Length(max=50,message = "A nacionalidade tem max de 50 caractres")
	private String nacionalidade;

	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
	private List<Livro> livros;

	public Autor() {
		super();
	}

	public Autor(Long id, String nome, String email, String nacionalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.nacionalidade = nacionalidade;
	}
	
	public Autor(AutorDTO autorDTO) {
		this.id = autorDTO.id();
		this.nome = autorDTO.nome();
		this.email = autorDTO.email();
		this.nacionalidade = autorDTO.nacionalidade();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
}
