package br.edu.ifba.meublog.dtos;

import br.edu.ifba.meublog.entidades.Categoria;
import br.edu.ifba.meublog.entidades.Post;

public record PostDTO(Long id, String titulo, String texto, String nomeUsuario, Categoria categoria) {
	public PostDTO(Post post) {
		this(post.getId(), post.getTitulo(), post.getTexto(), post.getUsuario().getNome(), post.getCategoria());
	}

}
