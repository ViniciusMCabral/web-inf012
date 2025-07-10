package com.api.biblioteca_escolar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.biblioteca_escolar.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
