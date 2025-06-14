package br.edu.ifba.meublog.repositorios;

import br.edu.ifba.meublog.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.meublog.entidades.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findByTituloContainingIgnoreCase(String titulo);
}
