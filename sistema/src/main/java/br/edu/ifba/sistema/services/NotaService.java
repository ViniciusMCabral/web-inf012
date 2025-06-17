package br.edu.ifba.sistema.services;

import java.util.List;

import br.edu.ifba.sistema.dtos.NotaDTO;
import br.edu.ifba.sistema.dtos.NotaForm;
import br.edu.ifba.sistema.entities.Nota;
import br.edu.ifba.sistema.respositories.NotaRepository;
import org.springframework.stereotype.Service;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import br.edu.ifba.sistema.entities.Disciplina;
import br.edu.ifba.sistema.respositories.DisciplinaRepository;

@Service
public class NotaService {

    private NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public List<NotaDTO> findAll() {
        return notaRepository.findAll().stream()
                .map(NotaDTO::new)
                .toList();
    }

    public NotaDTO findById(Long id) {
        return notaRepository.findById(id)
                .map(NotaDTO::new)
                .orElse(null);
    }

    public NotaDTO save(NotaDTO notaDTO) {
        return new NotaDTO(notaRepository.save(new Nota(notaDTO)));
    }


    public NotaDTO update(Long id, NotaDTO notaDTO) {
        Nota nota = notaRepository.findById(id)
                .orElse(null);
        if (nota == null) {
            return null;
        }else {
            nota.setAluno(notaDTO.aluno());
            nota.setNota(notaDTO.nota());
            return new NotaDTO(notaRepository.save(nota));
        }
    }

    public NotaDTO delete(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElse(null);
        if (nota == null) {
            return null;
        }else {
            notaRepository.delete(nota);
            return new NotaDTO(nota);
        }
    }
}
