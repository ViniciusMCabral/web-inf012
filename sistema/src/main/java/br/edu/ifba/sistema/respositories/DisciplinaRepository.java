package br.edu.ifba.sistema.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.sistema.entities.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	

}
