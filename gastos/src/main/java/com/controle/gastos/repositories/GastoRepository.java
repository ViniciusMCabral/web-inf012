package com.controle.gastos.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.controle.gastos.entities.Categoria;
import com.controle.gastos.entities.Gasto;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
	
	List<Gasto> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
	
	List<Gasto> findByCategoria(Categoria categoria);
	
	 @Query("SELECT g.categoria AS categoria, SUM(g.valor) AS total " +
	           "FROM gastos g " +
	           "WHERE MONTH(g.data) = :mes AND YEAR(g.data) = :ano " +
	           "GROUP BY g.categoria")
	    List<CategoriaTotal> totalByCategoria(@Param("mes") int mes, @Param("ano") int ano);
}
