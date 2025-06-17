package br.edu.ifba.sistema.controllers;

import java.net.URI;
import java.util.List;

import br.edu.ifba.sistema.dtos.NotaDTO;
import br.edu.ifba.sistema.services.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.sistema.dtos.DisciplinaDTO;
import br.edu.ifba.sistema.services.DisciplinaService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/notas")
public class NotaController {


    private NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    public List<NotaDTO> getAll() {
        return notaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDTO> getById(@PathVariable Long id) {

        NotaDTO nota = notaService.findById(id);

        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<NotaDTO> create(@RequestBody NotaDTO notaDTO, UriComponentsBuilder uriBuilder) {
        NotaDTO nota = notaService.save(notaDTO);
        URI location = uriBuilder.path("/api/notas/{id}").buildAndExpand(nota.id()).toUri();
        return ResponseEntity.created(location).body(nota);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<NotaDTO> update(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        NotaDTO nota = notaService.update(id, notaDTO);

        if (nota != null) {
            return ResponseEntity.ok(nota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        NotaDTO nota = notaService.delete(id);

        if (nota != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
