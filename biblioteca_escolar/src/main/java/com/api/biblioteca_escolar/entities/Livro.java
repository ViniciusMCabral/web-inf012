package com.api.biblioteca_escolar.entities;

import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

import com.api.biblioteca_escolar.dtos.LivroDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "livros")
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "O titulo não pode ser nulo") 
	@Length(min=2, max=150,message = "O titulo tem min de 2 caracteres e max de 150 caracteres")
	private String titulo;
	@NotNull(message = "O isbn não pode ser nulo") 
	@Size(min = 13, max = 13)
	private String isbn;
	@NotNull(message = "O ano de publicação não pode ser nulo") 
	@Min(value = 1500, message = "O ano de publicação deve ser no mínimo de 1500")
	private Integer anoPublicacao;
	@NotNull(message = "O Autor não pode ser nulo") 
	@ManyToOne
	private Autor autor;
	
	public Livro() {
		super();
	}
	
	public Livro(Long id, String titulo, String isbn, Integer anoPublicacao, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.isbn = isbn;
		this.anoPublicacao = anoPublicacao;
		this.autor = autor;
	}
	
	public Livro(LivroDTO livroDTO) {
		this.id = livroDTO.id();
		this.titulo = livroDTO.titulo();
		this.isbn = livroDTO.isbn();
		this.anoPublicacao = livroDTO.anoPublicacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(Integer anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
