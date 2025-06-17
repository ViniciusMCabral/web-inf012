package br.edu.ifba.sistema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import br.edu.ifba.sistema.entities.Disciplina;
import br.edu.ifba.sistema.respositories.DisciplinaRepository;

@Service
public class DisciplinaService {

	private DisciplinaRepository disciplinaRepository;
	
	public DisciplinaService(DisciplinaRepository disciplinaRepository) {
		this.disciplinaRepository = disciplinaRepository;
	}
	
	public List<DisciplinaDTO> findAll() {
		return disciplinaRepository.findAll().stream()
				.map(DisciplinaDTO::new)
				.toList();
	}
	
	public DisciplinaDTO findById(Long id) {
		return disciplinaRepository.findById(id)
				.map(DisciplinaDTO::new)
				.orElse(null);
	}
	
	public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
		return new DisciplinaDTO(disciplinaRepository.save(new Disciplina(disciplinaDTO)));
	}
	
	
	public DisciplinaDTO update(Long id, DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = disciplinaRepository.findById(id)
				.orElse(null);
		if (disciplina == null) {
			return null;
		}else {
			disciplina.setNome(disciplinaDTO.nome());
			disciplina.setCodigo(disciplinaDTO.codigo());
			return new DisciplinaDTO(disciplinaRepository.save(disciplina));
		}
	}
	
	public DisciplinaDTO delete(Long id) {
		Disciplina disciplina = disciplinaRepository.findById(id)
				.orElse(null);
		if (disciplina == null) {
			return null;
		}else {
			disciplinaRepository.delete(disciplina);
			return new DisciplinaDTO(disciplina);
		}
	}
}
