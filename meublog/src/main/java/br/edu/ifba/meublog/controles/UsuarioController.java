package br.edu.ifba.meublog.controles;


import java.net.URI;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifba.meublog.dtos.UsuarioDTO;
import br.edu.ifba.meublog.dtos.UsuarioForm;

import br.edu.ifba.meublog.servicos.UsuarioService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	@PostMapping
	public ResponseEntity<UsuarioForm> cadastrar(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder  uriBuilder) {
		UsuarioForm form=this.usuarioService.cadastrar(usuarioForm);
		URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(form.id()).toUri();
		return ResponseEntity.created(uri).body(form);
	}
	
	@GetMapping
	public List<UsuarioDTO> listar() {
		return this.usuarioService.listar();
	}
	
	@GetMapping("/buscarPorNome")
	public List<UsuarioDTO> buscarPorNome(String nome) {
		return this.usuarioService.buscarPorNome(nome);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		UsuarioDTO usuario = this.usuarioService.buscarPorId(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<UsuarioDTO> deletar(@PathVariable Long id) {
		UsuarioDTO usuario = this.usuarioService.deletar(id);
		if (usuario != null) {
			return ResponseEntity.ok(usuario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
