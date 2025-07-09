package br.edu.ifba.segurancaApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifba.segurancaApp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	UserDetails findByLogin(String username);
}
