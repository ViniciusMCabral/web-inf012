package br.edu.ifba.segurancaApp.dtos;

import br.edu.ifba.segurancaApp.entities.Usuario;

public record UsuarioForm(Long id, String nome, String login, String senha) {
	public UsuarioForm(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getSenha());
}}
