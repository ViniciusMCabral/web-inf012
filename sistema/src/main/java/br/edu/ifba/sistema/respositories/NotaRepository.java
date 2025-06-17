package br.edu.ifba.sistema.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.sistema.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
	
}
