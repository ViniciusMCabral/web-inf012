package br.edu.ifba.meublog.dtos;

import br.edu.ifba.meublog.entidades.Usuario;

public record UsuarioDTO(Long id, String nome, String login) {

	public UsuarioDTO(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getLogin());
	}
}
