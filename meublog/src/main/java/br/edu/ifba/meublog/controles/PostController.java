package br.edu.ifba.meublog.controles;


import br.edu.ifba.meublog.dtos.PostDTO;
import br.edu.ifba.meublog.servicos.PostService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}


	@PostMapping
	public ResponseEntity<PostDTO> cadastrar(@RequestBody PostDTO postDTO, UriComponentsBuilder  uriBuilder) {
		PostDTO dto=this.postService.cadastrar(postDTO);
		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping
	public List<PostDTO> listar() {
		return this.postService.listar();
	}

	@GetMapping("/buscarPorNome")
	public List<PostDTO> buscarPorTitulo(String titulo) {
		return this.postService.buscarPorTitulo(titulo);
	}

	@GetMapping("{id}")
	public ResponseEntity<PostDTO> buscarPorId(@PathVariable Long id) {
		PostDTO post = this.postService.buscarPorId(id);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<PostDTO> deletar(@PathVariable Long id) {
		PostDTO post = this.postService.deletar(id);
		if (post != null) {
			return ResponseEntity.ok(post);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
