package br.edu.ifba.meublog.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.meublog.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByNome(String nome);
	List<Usuario> findByNomeContainingIgnoreCase(String nome);
	
}
