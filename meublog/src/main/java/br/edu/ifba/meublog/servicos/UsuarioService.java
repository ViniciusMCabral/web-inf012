package br.edu.ifba.meublog.servicos;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.ifba.meublog.dtos.UsuarioDTO;
import br.edu.ifba.meublog.dtos.UsuarioForm;
import br.edu.ifba.meublog.entidades.Usuario;
import br.edu.ifba.meublog.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	public UsuarioForm cadastrar(UsuarioForm usuarioForm) {
		Usuario user = this.usuarioRepository.save(new Usuario(usuarioForm));
		return new UsuarioForm(user.getId(), user.getNome(), user.getLogin(), user.getSenha());
	}
	
	public List<UsuarioDTO> listar(){
		return this.usuarioRepository.findAll().stream()
				.map(UsuarioDTO::new)
				.toList();
	}
	
	public List<UsuarioDTO> buscarPorNome(String nome) {
		List<Usuario> usuarios = this.usuarioRepository.findByNomeContainingIgnoreCase(nome);
		return usuarios.stream()
				.map(UsuarioDTO::new)
				.toList();
	}
	
	public UsuarioDTO buscarPorId(Long id) {
		
		return this.usuarioRepository.findById(id)
				.map(usuario -> new UsuarioDTO(usuario))
				.orElse(null);
	}
	
	public UsuarioDTO deletar( Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).orElse(null);
		if (usuario != null) {
			this.usuarioRepository.delete(usuario);
			return new UsuarioDTO(usuario);
		}
		return null;
	}
}
