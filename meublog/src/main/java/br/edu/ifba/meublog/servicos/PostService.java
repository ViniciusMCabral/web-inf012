package br.edu.ifba.meublog.servicos;

import br.edu.ifba.meublog.dtos.PostDTO;
import br.edu.ifba.meublog.dtos.UsuarioDTO;
import br.edu.ifba.meublog.dtos.UsuarioForm;
import br.edu.ifba.meublog.entidades.Post;
import br.edu.ifba.meublog.entidades.Usuario;
import br.edu.ifba.meublog.repositorios.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
         this.postRepository = postRepository;
    }

    public PostDTO cadastrar(PostDTO postDTO) {
        Post post = this.postRepository.save(new Post(postDTO));
        return new PostDTO(post.getId(), post.getTitulo(), post.getTexto(), post.getUsuario().getNome(), post.getCategoria());
    }

    public List<PostDTO> listar(){
        return this.postRepository.findAll().stream()
                .map(PostDTO::new)
                .toList();
    }

    public List<PostDTO> buscarPorTitulo(String titulo) {
        List<Post> posts = this.postRepository.findByTituloContainingIgnoreCase(titulo);
        return posts.stream()
                .map(PostDTO::new)
                .toList();
    }

    public PostDTO buscarPorId(Long id) {

        return this.postRepository.findById(id)
                .map(post -> new PostDTO(post))
                .orElse(null);
    }

    public PostDTO deletar( Long id) {
        Post post = this.postRepository.findById(id).orElse(null);
        if (post != null) {
            this.postRepository.delete(post);
            return new PostDTO(post);
        }
        return null;
    }
}
