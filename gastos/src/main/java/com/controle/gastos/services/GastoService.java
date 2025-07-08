package com.controle.gastos.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.controle.gastos.dtos.GastoDTO;
import com.controle.gastos.entities.Categoria;
import com.controle.gastos.entities.Gasto;
import com.controle.gastos.repositories.CategoriaTotal;
import com.controle.gastos.repositories.GastoRepository;

@Service
public class GastoService {

	private GastoRepository gastoRepository;
	
	public GastoService(GastoRepository gastoRepository) {
		this.gastoRepository = gastoRepository;
	}
	
	public GastoDTO salvar(GastoDTO gastoDTO) {
		Gasto gasto = new Gasto(gastoDTO);
		gasto = gastoRepository.save(gasto);
		return new GastoDTO(gasto);
	}
	
	public List<GastoDTO> listarTodos() {
		List<Gasto> gastos = (List<Gasto>) gastoRepository.findAll();
		return gastos.stream().map(GastoDTO::new).toList();
	}
	
	public List<GastoDTO> listarPorMesAno(int mes, int ano) {
		 LocalDate dataInicio = LocalDate.of(ano, mes, 1);
		    LocalDate dataFim = dataInicio.withDayOfMonth(dataInicio.lengthOfMonth());
		    return gastoRepository.findByDataBetween(dataInicio, dataFim)
		            .stream()
		            .map(GastoDTO::new)
		            .toList();
	}
	
	public List<GastoDTO> listarPorCategoria(Categoria categoria) {
		List<Gasto> gastos = gastoRepository.findByCategoria(categoria);
		return gastos.stream().map(GastoDTO::new).toList();
	}

	public GastoDTO atualizar(GastoDTO gastoDTO, Long id) {
		Optional<Gasto> gastoOptional = gastoRepository.findById(id);
		if (gastoOptional.isEmpty()) {
			return null;
		}
		Gasto gasto = gastoOptional.get();
		gasto.setDescricao(gastoDTO.descricao());
		gasto.setValor(gastoDTO.valor());
		gasto.setData(gastoDTO.data());
		gasto.setCategoria(gastoDTO.categoria());
		gasto = gastoRepository.save(gasto);
		return new GastoDTO(gasto);
	}
	
	public GastoDTO deletar(Long id) {
		Optional<Gasto> gastoOptional = gastoRepository.findById(id);
		if (gastoOptional.isEmpty()) {
			return null;
		}
		Gasto gasto = gastoOptional.get();
		GastoDTO gastoDTO = new GastoDTO(gasto);
		gastoRepository.deleteById(id);
		return gastoDTO;
	}
	
	public Map<String, BigDecimal> relatorioMensal(int mes, int ano) {
        List<CategoriaTotal> resultados = gastoRepository.totalByCategoria(mes, ano);
        Map<String, BigDecimal> resposta = new HashMap<>();
        resultados.forEach(r -> 
            resposta.put(r.getCategoria().name(), r.getTotal())
        );
        return resposta;
    }
}
