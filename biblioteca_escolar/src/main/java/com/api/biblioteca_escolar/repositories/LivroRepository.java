package com.api.biblioteca_escolar.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.api.biblioteca_escolar.entities.Livro;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long>{

    Page<Livro> findByAutorId(Long autorId, Pageable pageable);

    Page<Livro> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
